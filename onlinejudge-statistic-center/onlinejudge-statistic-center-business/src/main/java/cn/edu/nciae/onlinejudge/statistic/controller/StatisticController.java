package cn.edu.nciae.onlinejudge.statistic.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.statistic.api.UserProblemServiceApi;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
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
        //        获取用户已经解决的问题列表
        List<Long> solvedProblemIdList = userProblemServiceApi.getSolvedProblemIdList(userInfo.getUserId());
        return ResponseResult.<List<Long>>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询用户已解决问题列表成功")
                .data(solvedProblemIdList)
                .build();
    }
}
