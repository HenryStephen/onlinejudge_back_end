package cn.edu.nciae.onlinejudge.oauth2.service;

import cn.edu.nciae.onlinejudge.user.api.PermissionServiceApi;
import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.Permission;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/15 13:57
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0",check = false)
    private UserInfoServiceApi userInfoServiceApi;

    @Reference(version = "1.0.0",check = false)
    private PermissionServiceApi permissionServiceApi;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 查询用户
        UserInfo userInfo = userInfoServiceApi.getByUserName(s);
        // 默认所有用户拥有 USER 权限
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        // 用户存在
        if (userInfo != null) {
            List<Permission> permissions = permissionServiceApi.selectPermissionByUserId(userInfo.getUserId());
            permissions.forEach(permission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getEnname());
                grantedAuthorities.add(grantedAuthority);
            });
            return new User(userInfo.getUserName(), userInfo.getUserPassword(), grantedAuthorities);
        }

        // 用户不存在
        else {
            return null;
        }
    }
}
