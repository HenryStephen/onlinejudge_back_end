package cn.edu.nciae.onlinejudge.judge.message.consumer;

import cn.edu.nciae.onlinejudge.commons.utils.MapperUtils;
import cn.edu.nciae.onlinejudge.commons.utils.OkHttpClientUtil;
import cn.edu.nciae.onlinejudge.judge.api.SubmissionServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Submission;
import cn.edu.nciae.onlinejudge.judge.vo.JudgeResponse;
import cn.edu.nciae.onlinejudge.judge.vo.JudgeResult;
import cn.edu.nciae.onlinejudge.judge.vo.SubmissionVO;
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

    /**
     * 创建submission
     * @param submissionVO
     * @throws Exception
     */
    @StreamListener("create-submission-input")
    public void createSubmission(SubmissionVO submissionVO) throws Exception {
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
