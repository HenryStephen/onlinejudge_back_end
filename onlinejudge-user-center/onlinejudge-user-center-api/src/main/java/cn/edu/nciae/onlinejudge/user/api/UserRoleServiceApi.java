package cn.edu.nciae.onlinejudge.user.api;

import cn.edu.nciae.onlinejudge.user.domain.UserRole;

public interface UserRoleServiceApi {
    /**
     * 根据用户id更新用户的角色信息
     * @param userRole
     * @return
     */
    boolean updateByUserId(UserRole userRole);

    /**
     * 保存user-role的关系
     * @param userRole
     * @return
     */
    boolean save(UserRole userRole);
}
