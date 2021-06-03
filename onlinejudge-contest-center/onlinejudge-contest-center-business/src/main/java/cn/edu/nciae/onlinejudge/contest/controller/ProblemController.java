package cn.edu.nciae.onlinejudge.contest.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.ProblemServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemListVO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemParam;
import cn.edu.nciae.onlinejudge.contest.api.CompetitionProblemServiceApi;
import cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionProblemDTO;
import cn.edu.nciae.onlinejudge.judge.api.LanguagesServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Languages;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

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
     * 获取竞赛题目列表(管理员)
     * @param offset
     * @param limit
     * @param problemParam
     * @return
     */
    @GetMapping("/competition/problem/admin")
    public ResponseResult<ProblemListVO> getCompetitionProblemListAdmin(@RequestParam("offset") Integer offset,
                                                                        @RequestParam("limit") Integer limit,
                                                                        ProblemParam problemParam){
        //首先找到公共竞赛包含的所有题目
        List<CompetitionProblemDTO> competitionProblemDTOS = competitionProblemServiceApi.list(problemParam.getContestId());
        //将所有题目的id放到一个List中
        List<Long> problemIdList = new ArrayList<>();
        for(CompetitionProblemDTO competitionProblemDTO : competitionProblemDTOS){
            problemIdList.add(competitionProblemDTO.getProblemId());
        }
        problemParam.setProblemIdList(problemIdList);
        Page page;
        if (problemParam.getPage() != null){
            page = new Page<ProblemDTO>(problemParam.getPage(), limit);
        } else {
            page = new Page<ProblemDTO>(1, limit);
        }
        IPage<ProblemDTO> problems = problemServiceApi.getProblemListPage(page, problemParam);
        // 设置题目的相关信息
        if(problems != null){
            for(ProblemDTO problemDTO : problems.getRecords()){
                for(CompetitionProblemDTO competitionProblemDTO : competitionProblemDTOS){
                    if(problemDTO.getProblemId().equals(competitionProblemDTO.getProblemId())){
                        problemDTO.setProblemDisplayId(competitionProblemDTO.getProblemDisplayId());
                        problemDTO.setProblemScore(competitionProblemDTO.getProblemScore());
                        problemDTO.setSolvedNumber(competitionProblemDTO.getSolvedNumber());
                        problemDTO.setSubmitNumber(competitionProblemDTO.getSubmitNumber());
                    }
                }
            }
        }
        if(problems != null && problems.getRecords() != null){
            return ResponseResult.<ProblemListVO>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("查询题目分页列表成功")
                    .data(ProblemListVO.builder()
                            .results(problems.getRecords())
                            .total(problems.getTotal())
                            .build())
                    .build();
        }else{
            return ResponseResult.<ProblemListVO>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("查询题目分页列表失败")
                    .data(null)
                    .build();
        }
    }

    /**
     * 查询具体竞赛题目
     * @param competitionId
     * @param problemDisplayId
     * @return
     */
    @GetMapping("/competition/{competitionId}/problem/{problemDisplayId}")
    public ResponseResult<ProblemDTO> getCompetitionProblemByDisplayId(@PathVariable("competitionId")Long competitionId, @PathVariable("problemDisplayId") Long problemDisplayId){
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
     * 查询具体竞赛题目(管理员)
     * @param competitionId
     * @param problemId
     * @return
     */
    @GetMapping("/competition/{competitionId}/{problemId}/admin")
    public ResponseResult<ProblemDTO> getCompetitionProblemByProblemId(@PathVariable("competitionId")Long competitionId, @PathVariable("problemId") Long problemId){
        ProblemDTO problemDTO = problemServiceApi.getProblemVOByPid(problemId);
//        找到题目支持的语言id列表
        List<Integer> languageIdList = problemServiceApi.getLanguageIdListByProblemId(problemId);
        List<String> languageNameList = new ArrayList<String>();
        if(languageIdList != null){
            for (Integer languageId : languageIdList){
                Languages languages = languagesServiceApi.getLanguageById(languageId);
                languageNameList.add(languages.getLanguageName());
            }
        }
//        设置题目支持的编程语言
        if(languageIdList.size() != 0) {
            problemDTO.setLanguages(languageNameList);
        }
        CompetitionProblem competitionProblem = competitionProblemServiceApi.getByCompetitionIdAndProblemId(competitionId, problemId);
        problemDTO.setProblemDisplayId(competitionProblem.getProblemDisplayId());
        problemDTO.setSolvedNumber(competitionProblem.getSolvedNumber());
        problemDTO.setSubmitNumber(competitionProblem.getSubmitNumber());
        problemDTO.setProblemScore(competitionProblem.getProblemScore());
        if (problemDTO != null) {
            return ResponseResult.<ProblemDTO>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("查询竞赛题目成功")
                    .data(problemDTO)
                    .build();
        } else {
            return ResponseResult.<ProblemDTO>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("查询竞赛题目失败")
                    .data(null)
                    .build();
        }
    }
}
