package cn.edu.nciae.onlinejudge.user.api;

import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.vo.UserInfoDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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

    /**
     * 查看用户分页列表
     * @param pageP
     * @param keyword
     * @return
     */
    IPage<UserInfoDTO> getUserInfoListPage(Page pageP, String keyword);

    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    UserInfoDTO getUserDtoByUserId(Long userId);

    /**
     * 根据用户id修改用户
     * @param userInfo
     * @return
     */
    boolean updateByUserId(UserInfo userInfo);

    /**
     * 获取用户是否被禁用
     * @param username
     * @return
     */
    Boolean getIsDisabledByUserName(String username);

    /**
     * 根据ac个数降序排序
     * @param page
     * @return
     */
    IPage<UserInfoDTO> getUserInfoListPageByACnumberDESC(Page page);

    /**
     * 检测用户名是否重复
     * @param username
     * @return
     */
    Boolean checkUserName(String username);

    /**
     * 检测邮箱是否重复
     * @param email
     * @return
     */
    Boolean checkEmail(String email);

    /**
     * 添加用户信息
     * @param userInfo
     * @return
     */
    UserInfo saveUserInfo(UserInfo userInfo);

    /**
     * 获取用户数量
     * @return
     */
    Integer getUserCount();
}
