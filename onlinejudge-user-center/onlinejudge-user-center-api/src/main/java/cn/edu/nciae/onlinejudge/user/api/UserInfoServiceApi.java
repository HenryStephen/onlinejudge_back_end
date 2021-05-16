package cn.edu.nciae.onlinejudge.user.api;

import cn.edu.nciae.onlinejudge.user.domain.UserInfo;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 19:48
 */
public interface UserInfoServiceApi {

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    UserInfo getByUserName(String userName);

    /**
     * 根据用户名模糊查询用户
     * @param userName
     * @return
     */
    List<UserInfo> getUserListByUserName(String userName);

    /**
     * 根据用户名修改用户Profile
     * @param userInfo
     * @param userName
     * @return
     */
    boolean updateProfile(UserInfo userInfo, String userName);

    /**
     * 修改密码
     * @param userNewPassWord
     * @param userName
     * @return
     */
    boolean updatePassword(String userNewPassWord, String userName);

    /**
     * 根据用户id查找用户
     * @param userId
     * @return
     */
    UserInfo getByUserId(Long userId);

    /**
     * 修改用户邮箱
     * @param newEmail
     * @param userName
     * @return
     */
    boolean updateEmail(String newEmail, String userName);

    /**
     * 根据用户id更新用户信息
     * @param userInfo
     * @param userId
     */
    boolean update(UserInfo userInfo, Long userId);
}
