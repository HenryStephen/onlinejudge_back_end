package cn.edu.nciae.onlinejudge.statistic.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.contest.api.CompetitionServiceApi;
import cn.edu.nciae.onlinejudge.judge.api.SubmissionServiceApi;
import cn.edu.nciae.onlinejudge.statistic.api.UserProblemServiceApi;
import cn.edu.nciae.onlinejudge.statistic.vo.DashInfo;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.vo.UserInfoDTO;
import cn.edu.nciae.onlinejudge.user.vo.UserInfoListVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/7 18:13
 */
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Reference(version = "1.0.0",check = false)
    private UserInfoServiceApi userInfoServiceApi;

    @Reference(version = "1.0.0",check = false)
    private UserProblemServiceApi userProblemServiceApi;

    @Reference(version = "1.0.0",check = false)
    private SubmissionServiceApi submissionServiceApi;

    @Reference(version = "1.0.0",check = false)
    private CompetitionServiceApi competitionServiceApi;

    /**
     * 获取用户已解决问题列表
     * @param username
     * @return
     */
    @GetMapping("/userProblem")
    public ResponseResult<List<Long>> getUserSolvedProblemList(@RequestParam(value = "username", required = false) String username){
        String userName;
        if(username == null){
            // 获取认证信息
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //获取用户名
            userName = authentication.getName();
        }else{
            userName = username;
        }
        //获取用户信息
        UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
        //获取用户已经解决的公共问题列表
        List<Long> solvedProblemIdList = userProblemServiceApi.getSolvedProblemIdList(userInfo.getUserId(), 0L);
        return ResponseResult.<List<Long>>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询用户已解决问题列表成功")
                .data(solvedProblemIdList)
                .build();
    }

    /**
     * 公共题目acm排行榜
     * @param pageNum
     * @param limit
     * @param rule
     * @return
     */
    @GetMapping("/userRank")
    public ResponseResult<UserInfoListVo> getUserRank(@RequestParam(value = "pageNum") Integer pageNum,
                                                      @RequestParam(value = "limit") Integer limit,
                                                      @RequestParam(value = "rule") String rule){
        Page page = null;
        if(pageNum != null){
            page = new Page<UserInfoDTO>(pageNum, limit);
        }else{
            page = new Page<UserInfoDTO>(pageNum, limit);
        }
        IPage<UserInfoDTO> userInfos = userInfoServiceApi.getUserInfoListPageByACnumberDESC(page);
        return ResponseResult.<UserInfoListVo>builder()
                .code(BusinessStatus.OK.getCode())
                .message("排名统计成功")
                .data(UserInfoListVo.builder()
                        .results(userInfos.getRecords())
                        .total(userInfos.getTotal())
                        .build())
                .build();
    }

    /**
     * 获取仪表盘信息
     * @return
     */
    @GetMapping("/dashboardInfo")
    public ResponseResult<DashInfo> getDashboardInfo(){
        Integer userCount= userInfoServiceApi.getUserCount();
        Integer submissionCount = submissionServiceApi.getSubmissionCount();
        Integer recentContestCount = competitionServiceApi.getRecentContestCount();
        return ResponseResult.<DashInfo>builder()
                .data(DashInfo.builder()
                        .userCount(userCount)
                        .totalSubmissionCount(submissionCount)
                        .recentContestCount(recentContestCount)
                        .build())
                .code(BusinessStatus.OK.getCode())
                .message("获取仪表盘信息成功")
                .build();
    }
}
