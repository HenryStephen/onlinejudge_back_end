package cn.edu.nciae.onlinejudge.judge.message.consumer;

import cn.edu.nciae.onlinejudge.commons.utils.MapperUtils;
import cn.edu.nciae.onlinejudge.commons.utils.OkHttpClientUtil;
import cn.edu.nciae.onlinejudge.content.api.ProblemServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import cn.edu.nciae.onlinejudge.judge.api.SubmissionServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Submission;
import cn.edu.nciae.onlinejudge.judge.vo.JudgeResponse;
import cn.edu.nciae.onlinejudge.judge.vo.JudgeResult;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionVO;
import cn.edu.nciae.onlinejudge.statistic.api.UserProblemServiceApi;
import cn.edu.nciae.onlinejudge.statistic.domain.UserProblem;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/5 21:51
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class SubmissionConsumer {

    @Value("${base.config.judge.judge_token}")
    private String judgeToken;

    @Value("${base.config.judge.url_judge_core}")
    private String url_judge_core;

    @Reference(version = "1.0.0",check = false)
    private SubmissionServiceApi submissionServiceApi;

    @Reference(version = "1.0.0",check = false)
    private ProblemServiceApi problemServiceApi;

    @Reference(version = "1.0.0",check = false)
    private UserProblemServiceApi userProblemServiceApi;

    @Reference(version = "1.0.0",check = false)
    private UserInfoServiceApi userInfoServiceApi;

    /**
     * 创建submission
     * @param submissionVO
     * @throws Exception
     */
    @StreamListener("create-submission-input")
    public void createSubmission(SubmissionVO submissionVO) throws Exception {
        System.out.println("consumer input");
        // 构造传输对象
        String submissionString = MapperUtils.obj2jsonIgnoreNull(submissionVO.getJudgeVO());
        // 获取响应的评测信息 json格式
        String jsonString = OkHttpClientUtil.getInstance().postJsonWithToken(url_judge_core, submissionString, judgeToken);
        String err = MapperUtils.json2pojoByTree(jsonString,"err", String.class);
        // 状态信息
        Integer status = null;
        // 使用时间
        Integer usedTime = null;
        // 使用内存
        Integer usedMemory = null;
        // 评测成功
        if(err == null){
            List<JudgeResponse> judgeResponseList = MapperUtils.json2listByTree(jsonString, "data", JudgeResponse.class);
            usedTime = maxUsedTime(judgeResponseList);
            usedMemory = maxUsedMemory(judgeResponseList);
            status = judgeResponseStatus(judgeResponseList);
        }
        // 评测失败
        else{
            String data = MapperUtils.json2pojoByTree(jsonString,"data", String.class);
            // 说明是编译错误
            if(err.equals("CompileError")){
                status = JudgeResult.COMPILE_ERROR.getCode();
            }
        }
        // 创建submission
        Submission submission = new Submission();
        submission.setSubmissionId(submissionVO.getSubmissionId());
        submission.setSubmissionUserId(submissionVO.getUserId());
        submission.setSubmissionProblemId(submissionVO.getProblemId());
        if(submissionVO.getContestId() != null){
            submission.setSubmissionContestId(submissionVO.getContestId());
        }else{
            submission.setSubmissionContestId(0L);
        }
        submission.setSubmissionLanguageId(submissionVO.getLanguageId());
        submission.setSubmissionSourceCode(submissionVO.getJudgeVO().getSrc());
        submission.setSubmissionCommitTime(new Date());
        submission.setSubmissionStatus(status);
        submission.setSubmissionUsedTime(usedTime);
        submission.setSubmissionUsedMemory(usedMemory);
        // 保存submission
        submissionServiceApi.save(submission);
        //未处在竞赛中时
        if(submissionVO.getContestId() == null){
            // 更新题目的解决人数和提交数量
            ProblemDTO problemDTO = problemServiceApi.getProblemVOByPid(submissionVO.getProblemId());
            Integer problemSolvedNumber = problemDTO.getProblemSolvedNumber();
            Integer problemSubmitNumber = problemDTO.getProblemSubmitNumber();
            Problem problem = new Problem();
            //提交问题的数量加一
            problem.setProblemSubmitNumber(problemSubmitNumber+1);
            if(status.equals(JudgeResult.SUCCESS.getCode())){
                //解决问题数量加一
                problem.setProblemSolvedNumber(problemSolvedNumber+1);
            }
            problemServiceApi.update(problem, submissionVO.getProblemId());
            //更新userInfo
            UserInfo userInfo = userInfoServiceApi.getByUserId(submissionVO.getUserId());
            Integer userSubmissionNumber = userInfo.getUserSubmissionNumber();
            Integer userSolveNumber = userInfo.getUserSolveNumber();
            userInfo = new UserInfo();
            userInfo.setUserSubmissionNumber(userSubmissionNumber+1);
            // 更新userProblem
            UserProblem userProblem = userProblemServiceApi.getStatusByUserIdAndProblemId(submissionVO.getUserId(), submissionVO.getProblemId());
            if(userProblem != null){
                // 如果状态不为 success,则修改
                if(!userProblem.getStatus().equals(JudgeResult.SUCCESS.getCode())){
                    // 如果此次的状态为success
                    if(status.equals(JudgeResult.SUCCESS.getCode())){
                        userProblem.setStatus(status);
                        userInfo.setUserSolveNumber(userSolveNumber+1);
                    }else{
                        userProblem.setStatus(Math.max(userProblem.getStatus(),status));
                    }
                }
            }else{
                // 如果不存在信息
                userProblem = new UserProblem();
                userProblem.setUserId(submissionVO.getUserId());
                userProblem.setProblemId(submissionVO.getProblemId());
                userProblem.setStatus(status);
                if(status.equals(JudgeResult.SUCCESS.getCode())){
                    userInfo.setUserSolveNumber(userSolveNumber+1);
                }
            }
            userInfoServiceApi.update(userInfo, submissionVO.getUserId());
            userProblemServiceApi.saveOrUpdate(userProblem);
        }

    }

    /**
     * 找到最大的使用时间
     * @param judgeResponseList
     * @return
     */
    private Integer maxUsedTime(List<JudgeResponse> judgeResponseList){
        Integer usedTime = null;
        if(judgeResponseList != null){
            usedTime = judgeResponseList.get(0).getCpu_time();
            for(JudgeResponse judgeResponse : judgeResponseList){
                if(judgeResponse.getCpu_time() >= usedTime){
                    usedTime = judgeResponse.getCpu_time();
                }
            }
        }
        return usedTime;
    }

    /**
     * 找到最大的使用内存
     * @param judgeResponseList
     * @return
     */
    private Integer maxUsedMemory(List<JudgeResponse> judgeResponseList){
        Integer usedMemory = null;
        if(judgeResponseList != null){
            usedMemory = judgeResponseList.get(0).getMemory();
            for(JudgeResponse judgeResponse : judgeResponseList){
                if(judgeResponse.getMemory() >= usedMemory){
                    usedMemory = judgeResponse.getMemory();
                }
            }
        }
        return usedMemory;
    }

    /**
     * 多个测试样例的评测结果
     * @param judgeResponseList
     * @return
     */
    private Integer judgeResponseStatus(List<JudgeResponse> judgeResponseList){
        Integer status = null;
        Integer successCount = 0;
        if(judgeResponseList != null){
            status = judgeResponseList.get(0).getResult();
            for(JudgeResponse judgeResponse : judgeResponseList){
                if(judgeResponse.getResult() == JudgeResult.SUCCESS.getCode()){
                    // 如果有正确的，则计数
                    successCount++;
                }else{
                    // 如果存在非sucess，则设置status为更大的值
                    status = Math.max(status, judgeResponse.getResult());
                }
            }
            if(successCount != 0 && successCount != judgeResponseList.size()){
                // 如果有正确的样例，也有非正确的样例，则设置为部分正确
                status = JudgeResult.PARTIAL_ACCEPTED.getCode();
            }
        }
        return status;
    }
}
