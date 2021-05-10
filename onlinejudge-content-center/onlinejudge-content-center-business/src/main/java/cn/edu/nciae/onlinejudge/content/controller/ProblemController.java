package cn.edu.nciae.onlinejudge.content.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.ProblemServiceApi;
import cn.edu.nciae.onlinejudge.content.api.SampleServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Checkpoint;
import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.vo.ProblemParam;
import cn.edu.nciae.onlinejudge.content.vo.ProblemListVO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import cn.edu.nciae.onlinejudge.content.message.provider.CheckpointProvider;
import cn.edu.nciae.onlinejudge.content.utils.FPSUtils;
import cn.edu.nciae.onlinejudge.judge.api.LanguagesServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Languages;
import cn.edu.nciae.onlinejudge.statistic.api.UserProblemServiceApi;
import cn.edu.nciae.onlinejudge.statistic.domain.UserProblem;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:03
 */
@RestController
@RequestMapping("/content/problem")
public class ProblemController {

    @Reference(version = "1.0.0", check = false)
    private ProblemServiceApi problemServiceApi;

    @Reference(version = "1.0.0", check = false)
    private SampleServiceApi sampleServiceApi;

    @Reference(version = "1.0.0", check = false)
    private LanguagesServiceApi languagesServiceApi;

    @Reference(version = "1.0.0", check = false)
    private UserProblemServiceApi userProblemServiceApi;

    @Reference(version = "1.0.0", check = false)
    private UserInfoServiceApi userInfoServiceApi;

    @Resource
    private CheckpointProvider checkpointProvider;


    /**
     * 查询题目分页列表
     * @param paging
     * @param offset
     * @param limit
     * @param problemParam
     * @return
     */
    @GetMapping
    public ResponseResult<ProblemListVO> getProblemList(@RequestParam("paging") Boolean paging,
                                                        @RequestParam("offset") Integer offset,
                                                        @RequestParam("limit") Integer limit,
                                                        ProblemParam problemParam) {
        if (paging) {
            Page page;
            if (problemParam.getPage() != null){
                page = new Page<ProblemDTO>(problemParam.getPage(), limit);
            } else {
                page = new Page<ProblemDTO>(1, limit);
            }
            IPage<ProblemDTO> problems = problemServiceApi.getProblemListPage(page, problemParam);
            // 获取认证信息
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //获取用户名
            String userName = authentication.getName();
//            如果不是匿名用户，已认证时
            if(!"anonymousUser".equals(userName)){
                //获取用户信息
                UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
                for(ProblemDTO problemDTO : problems.getRecords()){
                    UserProblem userProblem = userProblemServiceApi.getStatusByUserIdAndProblemId(userInfo.getUserId(), problemDTO.getProblemId());
                    if(userProblem != null){
                        problemDTO.setMyStatus(userProblem.getStatus());
                    }
                }
            }
            return ResponseResult.<ProblemListVO>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("查询题目分页列表成功")
                    .data(ProblemListVO.builder()
                            .results(problems.getRecords())
                            .total(problems.getTotal())
                            .build())
                    .build();
        }
        return ResponseResult.<ProblemListVO>builder()
                .code(BusinessStatus.FAIL.getCode())
                .message("查询题目分页列表失败")
                .data(null)
                .build();
    }

    /**
     * 根据题目id查看题目详细信息
     * @param problemId
     * @return
     */
    @GetMapping("/{problemId}")
    public ResponseResult<ProblemDTO> getProblem(@PathVariable("problemId") Long problemId){
        ProblemDTO problemDTO = problemServiceApi.getProblemVOByPid(problemId);
        List<Integer> languageIdList = problemServiceApi.getLanguageIdListByProblemId(problemId);
        List<String> languageNameList = new ArrayList<String>();
        if(languageIdList != null){
            for (Integer languageId : languageIdList){
                Languages languages = languagesServiceApi.getLanguageById(languageId);
                languageNameList.add(languages.getLanguageName());
            }
        }
        if(languageIdList.size() != 0) {
            problemDTO.setLanguages(languageNameList);
        }
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
        //获取用户信息
        UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
        UserProblem userProblem = userProblemServiceApi.getStatusByUserIdAndProblemId(userInfo.getUserId(), problemId);
        if(userProblem != null){
            problemDTO.setMyStatus(userProblem.getStatus());
        }
        if (problemDTO != null) {
            return ResponseResult.<ProblemDTO>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("查询题目成功")
                    .data(problemDTO)
                    .build();
        } else {
            return ResponseResult.<ProblemDTO>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("查询题目失败")
                    .data(null)
                    .build();
        }
    }

    /**
     * 添加题目集(根据 FPS)
     * @return
     */
    @PostMapping("/FPS")
    public ResponseResult<ProblemListVO> addProblemListByFPS(){
        List<ProblemDTO> problemDTOList = FPSUtils.fps2ProblemVO(Long.valueOf("1"), "./Doc/standard-test-fps.xml");
        for (ProblemDTO problemDTO : problemDTOList) {
            //重新接收，获得problemId
            problemDTO = problemServiceApi.insertOneProblemVO(problemDTO);
            //增加测试用例
            for (Checkpoint checkpoint : problemDTO.getCheckpoints()) {
                checkpoint.setProblemId(problemDTO.getProblemId());
            }
            //异步发送消息
            checkpointProvider.addCheckpointList(problemDTO.getCheckpoints());
        }
        return ResponseResult.<ProblemListVO>builder()
                .message("添加FPS成功")
                .code(BusinessStatus.OK.getCode())
                .data(ProblemListVO.builder()
                        .results(problemDTOList)
                        .total((long) problemDTOList.size())
                        .build())
                .build();
    }

    /**
     * 添加题目
     * @return
     */
    @PostMapping
    public ResponseResult<ProblemDTO> addProblem(@RequestBody ProblemDTO problemDTO){
        problemDTO = problemServiceApi.insertOneProblemVO(problemDTO);
        return ResponseResult.<ProblemDTO>builder()
                .message("添加题目成功")
                .code(BusinessStatus.OK.getCode())
                .data(problemDTO)
                .build();
    }

    /**
     * 删除题目并且删除相关信息
     * @param problemId
     * @return
     */
    @DeleteMapping("/{problemId}")
    public ResponseResult<Void> deleteProblem(@PathVariable("problemId") Long problemId){
        //删除题目
        boolean result = problemServiceApi.removeById(problemId);
        //删除样例输入输出
        result = sampleServiceApi.removeByProblemId(problemId) && result;
        //删除测试用例
        //to do
        //problem—tag 关联删除
        //to do
        if(result){
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("删除题目成功")
                    .build();
        }else{
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("删除题目失败")
                    .build();
        }
    }

    /**
     * 更新题目
     * @param problemId
     * @return
     */
    @PutMapping("/{problemId}")
    public ResponseResult<Void> updateProblem(@PathVariable("problemId") Long problemId, @RequestBody Problem problem){
        //删除题目
        boolean result = problemServiceApi.update(problem,problemId);
        if(result){
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("修改题目成功")
                    .build();
        }else{
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("修改题目失败")
                    .build();
        }
    }
}
