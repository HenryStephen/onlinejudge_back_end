package cn.edu.nciae.onlinejudge.contest.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.ProblemServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.contest.api.CompetitionProblemServiceApi;
import cn.edu.nciae.onlinejudge.contest.api.CompetitionServiceApi;
import cn.edu.nciae.onlinejudge.contest.vo.*;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 9:49
 */
@RestController
@RequestMapping("/contest/competition")
public class CompetitionController {

    @Reference(version = "1.0.0",check = false)
    private CompetitionServiceApi competitionServiceApi;

    @Reference(version = "1.0.0",check = false)
    private UserInfoServiceApi userInfoServiceApi;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    /**
     * 查看竞赛分页列表
     * @param offset
     * @param limit
     * @param competitionParam
     * @return
     */
    @GetMapping
    public ResponseResult<CompetitionListVO> getCompetitionList(@RequestParam("offset") Integer offset,
                                                                @RequestParam("limit") Integer limit,
                                                                CompetitionParam competitionParam){
        //设置分页
        Page page;
        if(competitionParam.getPage() != null){
            page = new Page<CompetitionDTO>(competitionParam.getPage(), limit);
        }else{
            page = new Page<CompetitionDTO>(1, limit);
        }
        IPage<CompetitionDTO> competitions = competitionServiceApi.getCompetitionListPage(page, competitionParam);
        for(CompetitionDTO competitionDTO : competitions.getRecords()){
            UserInfo userInfo = userInfoServiceApi.getByUserId(competitionDTO.getCompetitionCreateUser());
            competitionDTO.setCreateUserName(userInfo.getUserName());
            competitionDTO.setNow(new Date());
            competitionDTO.setCompetitionStatus(getCompetitionStatus(competitionDTO.getNow(), competitionDTO.getCompetitionStartTime(), competitionDTO.getCompetitionEndTime()));
        }
        return ResponseResult.<CompetitionListVO>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询竞赛分页列表成功")
                .data(CompetitionListVO.builder()
                        .results(competitions.getRecords())
                        .total(competitions.getTotal())
                        .build())
                .build();
    }

    /**
     * 查看竞赛
     * @param competitionId
     * @return
     */
    @GetMapping("/{competitionId}")
    public ResponseResult<CompetitionDTO> getCompetition(@PathVariable("competitionId") Long competitionId){
//        先查到competition
        CompetitionDTO competitionDTO = competitionServiceApi.getCompetitionVOById(competitionId);
        UserInfo userInfo = userInfoServiceApi.getByUserId(competitionDTO.getCompetitionCreateUser());
//        设置发起人
        competitionDTO.setCreateUserName(userInfo.getUserName());
//        设置当前时间
        competitionDTO.setNow(new Date());
//        设置当前竞赛状态
        competitionDTO.setCompetitionStatus(getCompetitionStatus(competitionDTO.getNow(), competitionDTO.getCompetitionStartTime(), competitionDTO.getCompetitionEndTime()));
        return ResponseResult.<CompetitionDTO>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询竞赛成功")
                .data(competitionDTO)
                .build();
    }

    /**
     * 验证竞赛密码
     * @param competitionPassword
     * @return
     */
    @PostMapping("/password")
    public ResponseResult<Void> checkCompetitionPassword(@RequestBody CompetitionPassword competitionPassword){
        CompetitionDTO competitionDTO = competitionServiceApi.getCompetitionVOById(competitionPassword.getCompetitionId());
        System.out.println(competitionPassword.toString());
        System.out.println(competitionDTO);
        if(competitionDTO == null || !passwordEncoder.matches(competitionPassword.getPassword(), competitionDTO.getCompetitionPassword())){
            return ResponseResult.<Void>builder()
                                .code(BusinessStatus.FAIL.getCode())
                                .message("竞赛密码不正确")
                                .build();
        }
        return ResponseResult.<Void>builder()
                .code(BusinessStatus.OK.getCode())
                .message("竞赛密码正确")
                .build();
    }


    /**
     * 获取竞赛的状态
     * @param nowTime
     * @param startTime
     * @param endTime
     * @return
     */
    private Integer getCompetitionStatus(Date nowTime, Date startTime, Date endTime){
        Integer status = null;
        if(nowTime.before(startTime)){
//            未开始
            return 1;
        }else if(nowTime.after(endTime)){
//            已结束
            return -1;
        }else if(nowTime.after(startTime) && nowTime.before(endTime)){
//            正在进行
            return 0;
        }
        return status;
    }
}
