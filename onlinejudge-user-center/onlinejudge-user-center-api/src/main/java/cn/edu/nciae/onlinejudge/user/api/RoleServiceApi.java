package cn.edu.nciae.onlinejudge.user.api;

import cn.edu.nciae.onlinejudge.user.domain.Role;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/27 17:16
 */
public interface RoleServiceApi {
    /**
     * 根据用户id查找对应角色
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(Long userId);

    /**
     * 根据角色名字获取角色
     * @param roleType
     * @return
     */
    Role getRoleByRoleName(String roleType);
}
