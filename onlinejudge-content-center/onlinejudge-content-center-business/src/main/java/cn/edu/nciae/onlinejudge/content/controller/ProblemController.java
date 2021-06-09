package cn.edu.nciae.onlinejudge.content.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.commons.utils.FilesUtils;
import cn.edu.nciae.onlinejudge.commons.utils.MapperUtils;
import cn.edu.nciae.onlinejudge.commons.utils.OkHttpClientUtil;
import cn.edu.nciae.onlinejudge.commons.utils.ZipUtils;
import cn.edu.nciae.onlinejudge.content.api.*;
import cn.edu.nciae.onlinejudge.content.domain.Checkpoint;
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
import cn.edu.nciae.onlinejudge.judge.api.SubmissionServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Languages;
import cn.edu.nciae.onlinejudge.statistic.api.UserProblemServiceApi;
import cn.edu.nciae.onlinejudge.statistic.domain.UserProblem;
import cn.edu.nciae.onlinejudge.user.api.RoleServiceApi;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.Role;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:03
 */
@RestController
@RequestMapping("/content/problem")
public class ProblemController {

    @Value("${base.config.judge.url_testcase_core}")
    private String url_testcase_core;

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

    @Reference(version = "1.0.0",check = false)
    private RoleServiceApi roleServiceApi;

    @Reference(version = "1.0.0",check = false)
    private ProblemTagServiceApi problemTagServiceApi;

    @Reference(version = "1.0.0",check = false)
    private SubmissionServiceApi submissionServiceApi;

    /**
     * 查询公共题目分页列表
     * @param offset
     * @param limit
     * @param problemParam
     * @return
     */
    @GetMapping
    public ResponseResult<ProblemListVO> getProblemList(@RequestParam(value = "offset",required = false) Integer offset,
                                                        @RequestParam(value = "limit",required = false) Integer limit,
                                                        ProblemParam problemParam) {
        //首先找到公共竞赛包含的所有题目
        List<CompetitionProblemDTO> competitionProblemDTOS = null;
        if(problemParam.getProblemRuleType() == null){
            competitionProblemDTOS = competitionProblemServiceApi.listByCompetitionId(0L);
        }else {
            // 带有题目规则的查询
            competitionProblemDTOS = competitionProblemServiceApi.listByCompetitionIdAndRuleType(0L, problemParam.getProblemRuleType());
        }
        //将所有题目的id放到一个List中
        List<Long> problemIdList = new ArrayList<>();
        if(competitionProblemDTOS != null && competitionProblemDTOS.size()>0){
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
            // 获取认证信息
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // 获取用户名
            String userName = authentication.getName();
            // 如果不是匿名用户，已认证时
            if(!"anonymousUser".equals(userName)){
                //获取用户信息
                UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
                // 设置用户解决的问题
                for(ProblemDTO problemDTO : problems.getRecords()){
                    UserProblem userProblem = userProblemServiceApi.getStatusByUserIdAndDisplayIdAndCompetitionId(userInfo.getUserId(), problemDTO.getProblemDisplayId(), 0L);
                    if(userProblem != null){
                        problemDTO.setMyStatus(userProblem.getStatus());
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
     * 查询公共题目分页列表(管理员)
     * @param offset
     * @param limit
     * @param problemParam
     * @return
     */
    @GetMapping("/admin")
    public ResponseResult<ProblemListVO> getProblemListAdmin(@RequestParam(value = "offset",required = false) Integer offset,
                                                        @RequestParam(value = "limit",required = false) Integer limit,
                                                        ProblemParam problemParam) {
        //首先找到公共竞赛包含的所有题目
        List<CompetitionProblemDTO> competitionProblemDTOS = null;
        if(problemParam.getProblemRuleType() == null){
            competitionProblemDTOS = competitionProblemServiceApi.listByCompetitionId(0L);
        }else {
            competitionProblemDTOS = competitionProblemServiceApi.listByCompetitionIdAndRuleType(0L, problemParam.getProblemRuleType());
        }
        //将所有题目的id放到一个List中
        List<Long> problemIdList = new ArrayList<>();
        if(competitionProblemDTOS != null && competitionProblemDTOS.size()>0){
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
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
        if(!"anonymousUser".equals(userName)){
            UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
            // 获取用户id
            Long userId = userInfo.getUserId();
            // 查出用户角色类型
            List<Role> roles = roleServiceApi.selectRoleByUserId(userId);
            String roleType = roles.get(0).getEnname();
            // 如果是管理员
            if("Admin".equals(roleType)){
                problemParam.setAddUserId(userId);
            }else if("Super Admin".equals(roleType)){
                // 如果是超级管理员
                problemParam.setAddUserId(null);
            }
        }
        Page page;
        if (problemParam.getPage() != null){
            page = new Page<ProblemDTO>(problemParam.getPage(), limit);
        } else {
            page = new Page<ProblemDTO>(1, limit);
        }
        IPage<ProblemDTO> problems = problemServiceApi.getProblemListPageAdmin(page, problemParam);
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
     * 查询公共题集具体题目信息
     * @param problemDisplayId
     * @return
     */
    @GetMapping("/{problemDisplayId}")
    public ResponseResult<ProblemDTO> getProblem(@PathVariable("problemDisplayId") Long problemDisplayId){
        // 首先获取到problemId
        CompetitionProblem competitionProblem = competitionProblemServiceApi.getByCompetitionIdAndDisplayId(0L, problemDisplayId);
        Long problemId = competitionProblem.getProblemId();
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
        competitionProblem = competitionProblemServiceApi.getByCompetitionIdAndProblemId(0L, problemId);
        problemDTO.setProblemDisplayId(competitionProblem.getProblemDisplayId());
        problemDTO.setSolvedNumber(competitionProblem.getSolvedNumber());
        problemDTO.setSubmitNumber(competitionProblem.getSubmitNumber());
        problemDTO.setProblemScore(competitionProblem.getProblemScore());
        problemDTO.setProblemRuleType(competitionProblem.getProblemRuleType());
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
        //如果不是匿名用户，已认证时
        if(!"anonymousUser".equals(userName)){
            //获取用户信息
            UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
            UserProblem userProblem = userProblemServiceApi.getStatusByUserIdAndDisplayIdAndCompetitionId(userInfo.getUserId(), problemDisplayId, 0L);
            // 设置mystatus
            if(userProblem != null){
                problemDTO.setMyStatus(userProblem.getStatus());
            }
        }
        // 根据竞赛id和题目id获得题目的统计信息
        List<Map<String, Long>> statistic = submissionServiceApi.getStatistic(0L, problemDTO.getProblemDisplayId());
        Map<Long, Long> statistic_info = new HashMap<>();
        for(Map<String,Long> elem : statistic){
            statistic_info.put(elem.get("status"), elem.get("statusCount"));
        }
        for (Map.Entry<Long, Long> entry : statistic_info.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        problemDTO.setStatistic_info(statistic_info);
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
    public ResponseResult<ProblemListVO> addProblemListByFPS(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        // 将multifile转换成file
        File file = FilesUtils.transferToFile(multipartFile);
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
        //获取用户信息
        UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
        List<ProblemDTO> problemDTOList = FPSUtils.fps2ProblemVO(userInfo.getUserName(),userInfo.getUserId(),file);
        for (ProblemDTO problemDTO : problemDTOList) {
            //上传测试用例并获得测试用例id
            String testcaseId = uploadTestCase(problemDTO.getCheckpoints());
            //设置测试用例id
            problemDTO.setProblemTestcaseId(testcaseId);
            //添加题目，样例
            problemDTO = problemServiceApi.insertOneProblemVO(problemDTO);
            // 添加problem_language
            List<String> languageList = problemDTO.getLanguages();
            if(languageList != null){
                for(String s : languageList){
                    Languages language = languagesServiceApi.getLanguageByLanguageName(s, false);
                    ProblemLanguage problemLanguage = new ProblemLanguage();
                    problemLanguage.setLanguageId(language.getLanguageId());
                    problemLanguage.setProblemId(problemDTO.getProblemId());
                    problemLanguageServiceApi.save(problemLanguage);
                }
            }
            // 添加到competition_problem中
            CompetitionProblem competitionProblem = new CompetitionProblem();
            if(problemDTO.getContestId() != null){
                competitionProblem.setCompetitionId(problemDTO.getContestId());
            }else{
                competitionProblem.setCompetitionId(0L);
            }
            competitionProblem.setProblemId(problemDTO.getProblemId());
            //获取某个竞赛或者公共题目中最大的展示id
            Long maxDisplayId = competitionProblemServiceApi.getMaxDisplayId(competitionProblem.getCompetitionId());
            if(maxDisplayId == null){
                maxDisplayId = 0L;
            }
            competitionProblem.setProblemDisplayId(maxDisplayId + 1);
            competitionProblem.setSubmitNumber(0);
            competitionProblem.setSolvedNumber(0);
            competitionProblem.setProblemRuleType("ACM");
            competitionProblemServiceApi.save(competitionProblem);
            // 添加tag 和 problem_tag
            List<Tag> tagList = problemDTO.getTags();
            if(tagList != null){
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
            }
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
     * 添加题目（公共+竞赛 公用）
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
        //添加题目、样例等信息
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
        Long maxDisplayId = competitionProblemServiceApi.getMaxDisplayId(competitionProblem.getCompetitionId());
        if(maxDisplayId == null){
            maxDisplayId = 0L;
        }
        competitionProblem.setProblemDisplayId(maxDisplayId + 1);
        competitionProblem.setSubmitNumber(0);
        competitionProblem.setSolvedNumber(0);
        competitionProblem.setProblemRuleType(problemDTO.getProblemRuleType());
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
     * 删除题目
     * @param problemId
     * @return
     */
    @DeleteMapping("/{problemId}")
    public ResponseResult<Void> deleteProblem(@PathVariable("problemId") Long problemId){
        //在这里只是删除competition_problem表中的关联关系
        //并不会删除具体题目
        boolean result = competitionProblemServiceApi.removeByCompetitionIdAndProblemId(0L,problemId);
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
     * 更新题目（公共+竞赛 公用）
     * @param problemDTO
     * @return
     */
    @PutMapping
    public ResponseResult<Void> updateProblem(@RequestBody ProblemDTO problemDTO){
        //更新题目、样例等信息
        problemDTO= problemServiceApi.updateProblemDTO(problemDTO);
        //更新problem_language
        List<String> languageList = problemDTO.getLanguages();
        if(languageList != null){
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
        competitionProblem.setProblemRuleType(problemDTO.getProblemRuleType());
        //更新competition_problem
        competitionProblemServiceApi.updateByCompetitionIdAndProblemId(competitionProblem);
        // 添加tag 和 problem_tag
        List<Tag> tagList = problemDTO.getTags();
        if(tagList != null){
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
        }
        return ResponseResult.<Void>builder()
                .code(BusinessStatus.OK.getCode())
                .message("修改题目成功")
                .build();

    }

    /**
     * 上传测试用例，返回测试用例id
     * @param checkpoints
     * @return
     * @throws IOException
     */
    private String uploadTestCase(List<Checkpoint> checkpoints) throws Exception {
        if(checkpoints != null) {
            String filesName = "";
            for (int i = 0; i < checkpoints.size(); i++) {
                File inFile = new File(String.valueOf(i+1) + ".in");
                File outFile = new File(String.valueOf(i+1) + ".out");
                FileUtils.write(inFile, checkpoints.get(i).getInput(), "utf8", false);
                FileUtils.write(outFile, checkpoints.get(i).getOutput(), "utf8", false);
                filesName+=(inFile.getName()+"|");
                filesName+=(outFile.getName()+"|");
            }
            //去掉最后一个 ｜
            filesName = filesName.substring(0, filesName.length() - 1);
            ZipUtils.zip(filesName,"test.zip",null);
            // 请求参数
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("file", new File("test.zip"));
            paramsMap.put("spj", false);
            // 返回Response
            Response response = OkHttpClientUtil.getInstance().uploadFile(url_testcase_core, paramsMap);
            String jsonString = response.body().string();
            // 获取error
            Object data = MapperUtils.json2pojoByTree(jsonString,"data", Object.class);
            String dataStr = MapperUtils.obj2json(data);
            String testcaseId = MapperUtils.json2pojoByTree(dataStr, "id", String.class);
            return testcaseId;
        }
        return "";
    }
}
