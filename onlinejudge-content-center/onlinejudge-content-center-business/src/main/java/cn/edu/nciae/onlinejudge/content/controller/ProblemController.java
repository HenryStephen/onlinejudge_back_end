package cn.edu.nciae.onlinejudge.content.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.*;
import cn.edu.nciae.onlinejudge.content.domain.ProblemLanguage;
import cn.edu.nciae.onlinejudge.content.domain.ProblemTag;
import cn.edu.nciae.onlinejudge.content.domain.Tag;
import cn.edu.nciae.onlinejudge.content.utils.FPSUtils;
import cn.edu.nciae.onlinejudge.content.vo.ProblemDTO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemListVO;
import cn.edu.nciae.onlinejudge.content.vo.ProblemParam;
import cn.edu.nciae.onlinejudge.contest.api.CompetitionProblemServiceApi;
import cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionProblemDTO;
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

    @Reference(version = "1.0.0", check = false)
    private CompetitionProblemServiceApi competitionProblemServiceApi;

    @Reference(version = "1.0.0", check = false)
    private ProblemLanguageServiceApi problemLanguageServiceApi;

    @Reference(version = "1.0.0", check = false)
    private TagServiceApi tagServiceApi;

    @Reference(version = "1.0.0", check = false)
    private ProblemTagServiceApi problemTagServiceApi;

    /**
     * 查询公共题目分页列表
     * @param offset
     * @param limit
     * @param problemParam
     * @return
     */
    @GetMapping
    public ResponseResult<ProblemListVO> getProblemList(@RequestParam("offset") Integer offset,
                                                        @RequestParam("limit") Integer limit,
                                                        ProblemParam problemParam) {
        //首先找到公共竞赛包含的所有题目
        List<CompetitionProblemDTO> competitionProblemDTOS = competitionProblemServiceApi.list(0L);
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
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
//            如果不是匿名用户，已认证时
        if(!"anonymousUser".equals(userName)){
            //获取用户信息
            UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
//                设置用户解决的问题
            for(ProblemDTO problemDTO : problems.getRecords()){
                UserProblem userProblem = userProblemServiceApi.getStatusByUserIdAndProblemId(userInfo.getUserId(), problemDTO.getProblemId());
                if(userProblem != null){
                    problemDTO.setMyStatus(userProblem.getStatus());
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
     * 根据题目id查看题目详细信息
     * @param problemId
     * @return
     */
    @GetMapping("/{problemId}")
    public ResponseResult<ProblemDTO> getProblem(@PathVariable("problemId") Long problemId){
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
        CompetitionProblem competitionProblem = competitionProblemServiceApi.getByCompetitionIdAndProblemId(0L, problemId);
        problemDTO.setProblemDisplayId(competitionProblem.getProblemDisplayId());
        problemDTO.setSolvedNumber(competitionProblem.getSolvedNumber());
        problemDTO.setSubmitNumber(competitionProblem.getSubmitNumber());
        problemDTO.setProblemScore(competitionProblem.getProblemScore());
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
        //获取用户信息
        UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
        UserProblem userProblem = userProblemServiceApi.getStatusByUserIdAndProblemId(userInfo.getUserId(), problemId);
//        设置mystatus
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
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
        //获取用户信息
        UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
        //设置添加用户id
        problemDTO.setAddUserId(userInfo.getUserId());
        //设置作者
        problemDTO.setProblemAuthor(userName);
//        添加题目、样例等信息
        problemDTO = problemServiceApi.insertOneProblemVO(problemDTO);
        // 添加problem_language
        List<String> languageList = problemDTO.getLanguages();
        for(String s : languageList){
            Languages language = languagesServiceApi.getLanguageByLanguageName(s, false);
            ProblemLanguage problemLanguage = new ProblemLanguage();
            problemLanguage.setLanguageId(language.getLanguageId());
            problemLanguage.setProblemId(problemDTO.getProblemId());
            problemLanguageServiceApi.save(problemLanguage);
        }
        // 添加到competition_problem中
        CompetitionProblem competitionProblem = new CompetitionProblem();
        if(problemDTO.getContestId() != null){
            competitionProblem.setCompetitionId(problemDTO.getContestId());
        }else{
            competitionProblem.setCompetitionId(0L);
        }
        competitionProblem.setProblemId(problemDTO.getProblemId());
        competitionProblem.setProblemDisplayId(problemDTO.getProblemDisplayId());
        competitionProblem.setSubmitNumber(0);
        competitionProblem.setSolvedNumber(0);
        competitionProblemServiceApi.save(competitionProblem);
        // 添加tag 和 problem_tag
        List<Tag> tagList = problemDTO.getTags();
        // 查出是否有该tag
        for(Tag tag : tagList){
            ProblemTag problemTag = new ProblemTag();
            problemTag.setProblemId(problemDTO.getProblemId());
            //根据标签名查找是否有标签
            Tag tagTemp = tagServiceApi.getTagByTagName(tag.getTagName());
            // 说明没有该标签
            if(tagTemp == null){
                tag.setTagDescription(tag.getTagName());
                // 添加新标签
                tag = tagServiceApi.saveTag(tag);
                problemTag.setTagId(tag.getTagId());
            }else{
                problemTag.setTagId(tagTemp.getTagId());
            }
            // 保存problemTag
            problemTagServiceApi.save(problemTag);
        }
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
     * @param problemDTO
     * @return
     */
    @PutMapping
    public ResponseResult<Void> updateProblem(@RequestBody ProblemDTO problemDTO){
        //更新题目、样例等信息
        problemDTO= problemServiceApi.updateProblemDTO(problemDTO);
        //更新problem_language
        List<String> languageList = problemDTO.getLanguages();
        //首先删除problem_language
        problemLanguageServiceApi.removeByProblemId(problemDTO.getProblemId());
        //添加problem_language
        for(String s : languageList){
            Languages language = languagesServiceApi.getLanguageByLanguageName(s, false);
            ProblemLanguage problemLanguage = new ProblemLanguage();
            problemLanguage.setLanguageId(language.getLanguageId());
            problemLanguage.setProblemId(problemDTO.getProblemId());
            problemLanguageServiceApi.save(problemLanguage);
        }
        // 添加到competition_problem中
        CompetitionProblem competitionProblem = new CompetitionProblem();
        if(problemDTO.getContestId() != null){
            competitionProblem.setCompetitionId(problemDTO.getContestId());
        }else{
            competitionProblem.setCompetitionId(0L);
        }
        competitionProblem.setProblemId(problemDTO.getProblemId());
        competitionProblem.setProblemDisplayId(problemDTO.getProblemDisplayId());
        competitionProblem.setSubmitNumber(0);
        competitionProblem.setSolvedNumber(0);
        //更新competition_problem
        competitionProblemServiceApi.updateByCompetitionIdAndProblemId(competitionProblem);
        // 添加tag 和 problem_tag
        List<Tag> tagList = problemDTO.getTags();
        // 首先删除所有的problem_tag
        problemTagServiceApi.removeByProblemId(problemDTO.getProblemId());
        // 查出是否有该tag
        for(Tag tag : tagList){
            ProblemTag problemTag = new ProblemTag();
            problemTag.setProblemId(problemDTO.getProblemId());
            //根据标签名查找是否有标签
            Tag tagTemp = tagServiceApi.getTagByTagName(tag.getTagName());
            // 说明没有该标签
            if(tagTemp == null){
                tag.setTagDescription(tag.getTagName());
                // 添加新标签
                tag = tagServiceApi.saveTag(tag);
                problemTag.setTagId(tag.getTagId());
            }else{
                problemTag.setTagId(tagTemp.getTagId());
            }
            // 保存problemTag
            problemTagServiceApi.save(problemTag);
        }
        return ResponseResult.<Void>builder()
                .code(BusinessStatus.OK.getCode())
                .message("修改题目成功")
                .build();

    }
}
