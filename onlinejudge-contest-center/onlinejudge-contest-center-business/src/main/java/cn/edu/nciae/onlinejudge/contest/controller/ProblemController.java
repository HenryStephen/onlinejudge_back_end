package cn.edu.nciae.onlinejudge.contest.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.ProblemServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemListVO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemParam;
import cn.edu.nciae.onlinejudge.contest.api.CompetitionProblemServiceApi;
import cn.edu.nciae.onlinejudge.contest.api.CompetitionServiceApi;
import cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionDTO;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionProblemDTO;
import cn.edu.nciae.onlinejudge.contest.vo.PublicParam;
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

    @Reference(version = "1.0.0",check = false)
    private CompetitionServiceApi competitionServiceApi;

    @Reference(version = "1.0.0",check = false)
    private UserInfoServiceApi userInfoServiceApi;

    @Reference(version = "1.0.0",check = false)
    private UserProblemServiceApi userProblemServiceApi;


    /**
     * 获取竞赛题目列表
     * @param competitionId
     * @return
     */
    @GetMapping("/competition/{competitionId}/problem")
    public ResponseResult<List<CompetitionProblemDTO>> getCompetitionProblemList(@PathVariable("competitionId") Long competitionId){
        List<CompetitionProblemDTO> competitionProblemDTOS = competitionProblemServiceApi.listByCompetitionId(competitionId);
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取用户名
        String userName = authentication.getName();
        UserInfo userInfo = null;
        //如果不是匿名用户，已认证时
        if(!"anonymousUser".equals(userName)){
            //获取用户信息
            userInfo = userInfoServiceApi.getByUserName(userName);
        }
        if(competitionProblemDTOS.size()>0){
            // 设置题目标题
            for (CompetitionProblemDTO competitionProblemDTO : competitionProblemDTOS){
                Problem problem = problemServiceApi.getProblemById(competitionProblemDTO.getProblemId());
                competitionProblemDTO.setProblemTiltle(problem.getProblemTitle());
                if(userInfo != null){
                    UserProblem userProblem = userProblemServiceApi.getStatusByUserIdAndDisplayIdAndCompetitionId(userInfo.getUserId(), competitionProblemDTO.getProblemDisplayId(), competitionProblemDTO.getCompetitionId());
                    // 设置mystatus
                    if(userProblem != null){
                        competitionProblemDTO.setMyStatus(userProblem.getStatus());
                    }
                }
            }
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
        List<CompetitionProblemDTO> competitionProblemDTOS = competitionProblemServiceApi.listByCompetitionId(problemParam.getContestId());
        //将所有题目的id放到一个List中
        List<Long> problemIdList = new ArrayList<>();
        if(competitionProblemDTOS.size()>0){
            for(CompetitionProblemDTO competitionProblemDTO : competitionProblemDTOS){
                problemIdList.add(competitionProblemDTO.getProblemId());
            }
            problemParam.setProblemIdList(problemIdList);
        }
        if(problemIdList.size() == 0){
            return ResponseResult.<ProblemListVO>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("查询题目分页列表成功")
                    .data(null)
                    .build();
        }
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
                        problemDTO.setProblemRuleType(competitionProblemDTO.getProblemRuleType());
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
        //先查出具体problemid
        CompetitionProblem competitionProblem = competitionProblemServiceApi.getByCompetitionIdAndDisplayId(competitionId, problemDisplayId);
        ProblemDTO problemDTO = problemServiceApi.getProblemVOByPid(competitionProblem.getProblemId());
        //设置展示id
        problemDTO.setProblemDisplayId(problemDisplayId);
        //查出支持的编程语言列表
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
        // 找到题目支持的语言id列表
        List<Integer> languageIdList = problemServiceApi.getLanguageIdListByProblemId(problemId);
        List<String> languageNameList = new ArrayList<String>();
        if(languageIdList != null){
            for (Integer languageId : languageIdList){
                Languages languages = languagesServiceApi.getLanguageById(languageId);
                languageNameList.add(languages.getLanguageName());
            }
        }
        // 设置题目支持的编程语言
        if(languageIdList.size() != 0) {
            problemDTO.setLanguages(languageNameList);
        }
        CompetitionProblem competitionProblem = competitionProblemServiceApi.getByCompetitionIdAndProblemId(competitionId, problemId);
        problemDTO.setProblemDisplayId(competitionProblem.getProblemDisplayId());
        problemDTO.setSolvedNumber(competitionProblem.getSolvedNumber());
        problemDTO.setSubmitNumber(competitionProblem.getSubmitNumber());
        problemDTO.setProblemScore(competitionProblem.getProblemScore());
        problemDTO.setProblemRuleType(competitionProblem.getProblemRuleType());
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

    /**
     * 删除竞赛题目
     * @param competitionId
     * @param problemId
     * @return
     */
    @DeleteMapping("/competition/{competitionId}/{problemId}/admin")
    public ResponseResult<Void> deleteCompetitionProblem(@PathVariable("competitionId")Long competitionId, @PathVariable("problemId") Long problemId){
        //在这里只是删除competition_problem表中的关联关系
        //并不会删除具体题目
        boolean result = competitionProblemServiceApi.removeByCompetitionIdAndProblemId(competitionId,problemId);
        if(result){
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("删除竞赛题目成功")
                    .build();
        }else{
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("删除竞赛题目失败")
                    .build();
        }
    }


    /**
     * 将竞赛题目公共化
     * @param publicParam
     * @return
     */
    @PostMapping("/problem/makePublic")
    public ResponseResult<Void> makeCompetitionProblemPublic(@RequestBody PublicParam publicParam){
        CompetitionProblem competitionProblem = competitionProblemServiceApi.getByCompetitionIdAndProblemId(0L, publicParam.getProblemId());
        if(competitionProblem == null){
            competitionProblem = new CompetitionProblem();
            competitionProblem.setCompetitionId(0L);
            competitionProblem.setProblemId(publicParam.getProblemId());
            Long maxDisplayId = competitionProblemServiceApi.getMaxDisplayId(competitionProblem.getCompetitionId());
            if(maxDisplayId == null){
                maxDisplayId = 0L;
            }
            competitionProblem.setProblemDisplayId(maxDisplayId + 1);
            competitionProblem.setProblemScore(0L);
            competitionProblem.setSubmitNumber(0);
            competitionProblem.setSolvedNumber(0);
            ProblemDTO problemDTO = problemServiceApi.getProblemVOByPid(publicParam.getProblemId());
            competitionProblem.setProblemRuleType(problemDTO.getProblemRuleType());
            competitionProblemServiceApi.save(competitionProblem);
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.OK.getCode())
                    .data(null)
                    .message("将竞赛题目公共化成功").build();
        }else{
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .data(null)
                    .message("该题目是公共题目").build();
        }
    }

    /**
     * 从公共题目中添加题目到竞赛中
     * @param publicParam
     * @return
     */
    @PostMapping("/problem/addPublic")
    public ResponseResult<Void> addPublicProblem(@RequestBody PublicParam publicParam){
        CompetitionProblem competitionProblem = competitionProblemServiceApi.getByCompetitionIdAndProblemId(publicParam.getCompetitionId(), publicParam.getProblemId());
        if(competitionProblem == null){
            competitionProblem = new CompetitionProblem();
            competitionProblem.setCompetitionId(publicParam.getCompetitionId());
            competitionProblem.setProblemId(publicParam.getProblemId());
            Long maxDisplayId = competitionProblemServiceApi.getMaxDisplayId(competitionProblem.getCompetitionId());
            if(maxDisplayId == null){
                maxDisplayId = 0L;
            }
            competitionProblem.setProblemDisplayId(maxDisplayId + 1);
            competitionProblem.setProblemScore(0L);
            competitionProblem.setSubmitNumber(0);
            competitionProblem.setSolvedNumber(0);
            CompetitionDTO competitionDTO = competitionServiceApi.getCompetitionVOById(publicParam.getCompetitionId());
            competitionProblem.setProblemRuleType(competitionDTO.getCompetitionRuleType());
            competitionProblemServiceApi.save(competitionProblem);
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.OK.getCode())
                    .data(null)
                    .message("添加公共题目到竞赛题目成功").build();
        }else{
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .data(null)
                    .message("该题目存在").build();
        }
    }
}
