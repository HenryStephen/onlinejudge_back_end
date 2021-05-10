package cn.edu.nciae.onlinejudge.contest.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.ProblemServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import cn.edu.nciae.onlinejudge.contest.api.CompetitionProblemServiceApi;
import cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionProblemDTO;
import cn.edu.nciae.onlinejudge.judge.api.LanguagesServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Languages;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 16:16
 */
@RestController
@RequestMapping("/contest")
public class ProblemController {

    @Reference(version = "1.0.0",check = false)
    private CompetitionProblemServiceApi competitionProblemServiceApi;

    @Reference(version = "1.0.0",check = false)
    private ProblemServiceApi problemServiceApi;

    @Reference(version = "1.0.0",check = false)
    private LanguagesServiceApi languagesServiceApi;


    /**
     * 获取竞赛题目列表
     * @param competitionId
     * @return
     */
    @GetMapping("/competition/{competitionId}/problem")
    public ResponseResult<List<CompetitionProblemDTO>> getCompetitionProblemList(@PathVariable("competitionId") Long competitionId){
        List<CompetitionProblemDTO> competitionProblemDTOS = competitionProblemServiceApi.list(competitionId);
        // 设置题目标题
        for (CompetitionProblemDTO competitionProblemDTO : competitionProblemDTOS){
            Problem problem = problemServiceApi.getProblemById(competitionProblemDTO.getProblemId());
            competitionProblemDTO.setProblemTiltle(problem.getProblemTitle());
        }
        return ResponseResult.<List<CompetitionProblemDTO>>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询竞赛题目列表成功")
                .data(competitionProblemDTOS)
                .build();
    }

    /**
     * 查询具体竞赛题目
     * @param competitionId
     * @param problemDisplayId
     * @return
     */
    @GetMapping("/competition/{competitionId}/problem/{problemDisplayId}")
    public ResponseResult<ProblemDTO> getCompetitionProblem(@PathVariable("competitionId")Long competitionId, @PathVariable("problemDisplayId") Long problemDisplayId){
//        先查出具体problemid
        CompetitionProblem competitionProblem = competitionProblemServiceApi.getByCompetitionIdAndDisplayId(competitionId, problemDisplayId);
        ProblemDTO problemDTO = problemServiceApi.getProblemVOByPid(competitionProblem.getProblemId());
//        将problemid修改成展示id
        problemDTO.setProblemId(problemDisplayId);
        List<Integer> languageIdList = problemServiceApi.getLanguageIdListByProblemId(competitionProblem.getProblemId());
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
//        获取mystatus

//        // 获取认证信息
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        //获取用户名
//        String userName = authentication.getName();
//        //获取用户信息
//        UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
//        UserProblem userProblem = userProblemServiceApi.getStatusByUserIdAndProblemId(userInfo.getUserId(), problemId);
//        problemDTO.setMyStatus(userProblem.getStatus());

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
}
