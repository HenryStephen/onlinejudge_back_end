package cn.edu.nciae.onlinejudge.user.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.statistic.api.UserProblemServiceApi;
import cn.edu.nciae.onlinejudge.user.api.RoleServiceApi;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.Role;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.dto.ProfileDTO;
import cn.edu.nciae.onlinejudge.user.vo.UserInfoProfileVo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/18 21:32
 */
@RestController
@RequestMapping("/userInfo/profile")
public class ProfileController {

    @Reference(version = "1.0.0",check = false)
    private UserInfoServiceApi userInfoServiceApi;

    @Reference(version = "1.0.0",check = false)
    private RoleServiceApi roleServiceApi;

    @Reference(version = "1.0.0",check = false)
    private UserProblemServiceApi userProblemServiceApi;

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping
    public ResponseResult<ProfileDTO> getUserInfo(@RequestParam(value = "username", required = false) String username){
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
//        获取用户角色信息
        List<Role> roleList = roleServiceApi.selectRoleByUserId(userInfo.getUserId());
//        获取用户已经解决的问题列表
        List<Long> solvedProblemIdList = userProblemServiceApi.getSolvedProblemIdList(userInfo.getUserId());
        if(userInfo != null){
            return ResponseResult.<ProfileDTO>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("获取用户信息成功")
                    .data(ProfileDTO.builder()
                        .userInfo(userInfo)
                        .roleType(roleList.get(0).getEnname())
                        .problemPermission(getProblemPermission(roleList.get(0).getEnname()))
                        .solvedProblemIdList(solvedProblemIdList)
                        .build()
                    ).build();
        }else{
            return ResponseResult.<ProfileDTO>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("获取用户信息失败")
                    .data(null)
                    .build();
        }
    }

    /**
     * 修改用户Profile信息
     * @return
     */
    @PutMapping
    public ResponseResult<ProfileDTO> updateUserInfoProfile(@RequestBody UserInfoProfileVo userInfoProfileVo){
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名
        String userName = authentication.getName();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoProfileVo, userInfo);
        boolean result = userInfoServiceApi.updateProfile(userInfo, userName);
        if(result){
            //获取用户信息
            userInfo = userInfoServiceApi.getByUserName(userName);
//        获取用户角色信息
            List<Role> roleList = roleServiceApi.selectRoleByUserId(userInfo.getUserId());
//        获取用户已经解决的问题列表
            List<Long> solvedProblemIdList = userProblemServiceApi.getSolvedProblemIdList(userInfo.getUserId());
            return ResponseResult.<ProfileDTO>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("修改Profile信息成功")
                    .data(ProfileDTO.builder()
                            .userInfo(userInfo)
                            .roleType(roleList.get(0).getEnname())
                            .problemPermission(getProblemPermission(roleList.get(0).getEnname()))
                            .solvedProblemIdList(solvedProblemIdList)
                            .build())
                    .build();
        }else{
            return ResponseResult.<ProfileDTO>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("修改Profile信息失败")
                    .data(null)
                    .build();
        }
    }

    /**
     * 获取用户操作问题的权限
     * @param roleType
     * @return
     */
    private String getProblemPermission(String roleType){
        if("Super Admin".equals(roleType)){
            return "All";
        }else if("Admin".equals(roleType)){
            return "Own";
        }else if("Regular User".equals(roleType)){
            return "None";
        }else {
            return "None";
        }
    }
}
