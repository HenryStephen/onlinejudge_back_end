package cn.edu.nciae.onlinejudge.user.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.vo.EmailParam;
import cn.edu.nciae.onlinejudge.user.vo.PasswordParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/18 22:23
 */
@RestController
@RequestMapping("/userInfo/account")
public class AccountController {

    @Reference(version = "1.0.0",check = false)
    private UserInfoServiceApi userInfoServiceApi;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @PutMapping("/password")
    public ResponseResult<Void> updatePassword(@RequestBody PasswordParam passwordParam){
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
        //获取用户
        UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
        // 旧密码正确
        if (passwordEncoder.matches(passwordParam.getOldPassword(), userInfo.getUserPassword())) {
            boolean result = userInfoServiceApi.updatePassword(passwordEncoder.encode(passwordParam.getNewPassword()), userName);
            if (result) {
                return ResponseResult.<Void>builder()
                        .code(BusinessStatus.OK.getCode())
                        .message("修改密码成功")
                        .build();
            }
        }
        // 旧密码错误
        else {
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("旧密码不匹配，请重试")
                    .build();
        }
        return ResponseResult.<Void>builder()
                .code(BusinessStatus.FAIL.getCode())
                .message("修改密码失败")
                .build();
    }

    @PutMapping("/email")
    public ResponseResult<Void> updateEmail(@RequestBody EmailParam emailParam){
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
        //获取用户
        UserInfo userInfo = userInfoServiceApi.getByUserName(userName);
        // 旧密码正确
        if (passwordEncoder.matches(emailParam.getPassword(), userInfo.getUserPassword())) {
            boolean result = userInfoServiceApi.updateEmail(emailParam.getNewEmail(), userName);
            if (result) {
                return ResponseResult.<Void>builder()
                        .code(BusinessStatus.OK.getCode())
                        .message("修改邮箱成功")
                        .build();
            }
        }else{
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("密码不匹配，请重试")
                    .build();
        }
        return ResponseResult.<Void>builder()
                .code(BusinessStatus.FAIL.getCode())
                .message("修改邮箱失败")
                .build();
    }
}
