package cn.edu.nciae.onlinejudge.user.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.user.api.RoleServiceApi;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.api.UserRoleServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.Role;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.domain.UserRole;
import cn.edu.nciae.onlinejudge.user.vo.UserInfoDTO;
import cn.edu.nciae.onlinejudge.user.vo.UserInfoListVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/userInfo/user")
public class UserController {

    @Reference(version = "1.0.0",check = false)
    private UserInfoServiceApi userInfoServiceApi;

    @Reference(version = "1.0.0",check = false)
    private RoleServiceApi roleServiceApi;

    @Reference(version = "1.0.0",check = false)
    private UserRoleServiceApi userRoleServiceApi;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    /**
     * 获取用户分页列表（管理员）
     * @param page
     * @param limit
     * @param keyword
     * @return
     */
    @GetMapping
    public ResponseResult<UserInfoListVo> getUserList(@RequestParam(value = "page",required = false) Integer page,
                                                      @RequestParam(value = "limit",required = false)Integer limit,
                                                      @RequestParam(value = "keyword",required = false) String keyword){

        Page pageP;
        if (page != null){
            pageP = new Page<UserInfoDTO>(page, limit);
        } else {
            pageP = new Page<UserInfoDTO>(1, limit);
        }
        IPage<UserInfoDTO> userInfos = userInfoServiceApi.getUserInfoListPage(pageP, keyword);
        return ResponseResult.<UserInfoListVo>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询用户分页列表成功")
                .data(UserInfoListVo.builder()
                        .results(userInfos.getRecords())
                        .total(userInfos.getTotal())
                        .build())
                .build();
    }

    /**
     * 根据userId查找用户
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseResult<UserInfoDTO> getUser(@PathVariable("userId") Long userId){
        UserInfoDTO userInfoDTO = userInfoServiceApi.getUserDtoByUserId(userId);
        if(userInfoDTO != null){
            return ResponseResult.<UserInfoDTO>builder()
                    .message("查找用户成功")
                    .data(userInfoDTO)
                    .code(BusinessStatus.OK.getCode())
                    .build();
        } else {
            return ResponseResult.<UserInfoDTO>builder()
                    .message("查找用户失败")
                    .data(null)
                    .code(BusinessStatus.FAIL.getCode())
                    .build();
        }
    }

    /**
     * 更新用户（管理员）
     * @param userInfoDTO
     * @return
     */
    @PutMapping
    public ResponseResult<Void> updateUser(@RequestBody UserInfoDTO userInfoDTO){
        // 先更新用户信息
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfo);
        if("".equals(userInfo.getUserPassword())){
            userInfo.setUserPassword(null);
        }else{//代表有密码
            userInfo.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
        }
        boolean result = userInfoServiceApi.updateByUserId(userInfo);
        // 再更新用户的角色信息
        String roleType = userInfoDTO.getRoleType();
        Role role = roleServiceApi.getRoleByRoleName(roleType);
        UserRole userRole = new UserRole();
        userRole.setUserId(userInfoDTO.getUserId());
        userRole.setRoleId(role.getId());
        boolean result1 = userRoleServiceApi.updateByUserId(userRole);
        if(result && result1){
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.OK.getCode())
                    .message("更新用户成功")
                    .data(null)
                    .build();
        }else {
            return ResponseResult.<Void>builder()
                    .code(BusinessStatus.FAIL.getCode())
                    .message("更新用户失败")
                    .data(null)
                    .build();
        }
    }
}
