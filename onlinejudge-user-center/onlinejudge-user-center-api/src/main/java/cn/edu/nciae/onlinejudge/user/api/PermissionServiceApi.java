package cn.edu.nciae.onlinejudge.user.api;

import cn.edu.nciae.onlinejudge.user.domain.Permission;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 19:47
 */
public interface PermissionServiceApi {

    /**
     * 根据用户id查找对应权限
     * @param userId
     * @return
     */
    List<Permission> selectPermissionByUserId(Long userId);

}
