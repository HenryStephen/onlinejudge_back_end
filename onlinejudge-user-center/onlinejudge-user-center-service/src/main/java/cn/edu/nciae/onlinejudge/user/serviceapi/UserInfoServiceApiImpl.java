package cn.edu.nciae.onlinejudge.user.serviceapi;

import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.mapper.UserInfoMapper;
import cn.edu.nciae.onlinejudge.user.service.impl.UserInfoServiceImpl;
import cn.edu.nciae.onlinejudge.user.vo.UserInfoDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 19:52
 */
@Service
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class UserInfoServiceApiImpl extends UserInfoServiceImpl implements UserInfoServiceApi {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    @Override
    public UserInfo getByUserName(String userName) {
        return super.getOne(new QueryWrapper<UserInfo>().eq("user_name",userName));
    }

    /**
     * 根据用户名模糊查询用户
     *
     * @param userName
     * @return
     */
    @Override
    public List<UserInfo> getUserListByUserName(String userName) {
        return super.list(new QueryWrapper<UserInfo>().like("user_name",userName));
    }

    /**
     * 根据用户名修改用户Profile
     *
     * @param userInfo
     * @param userName
     * @return
     */
    @Override
    public boolean updateProfile(UserInfo userInfo, String userName) {
        return super.update(userInfo, new UpdateWrapper<UserInfo>().eq("user_name",userName));
    }

    /**
     * 修改密码
     *
     * @param userNewPassWord
     * @param userName
     * @return
     */
    @Override
    public boolean updatePassword(String userNewPassWord, String userName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserPassword(userNewPassWord);
        return super.update(userInfo, new UpdateWrapper<UserInfo>().eq("user_name",userName));
    }

    /**
     * 根据用户id查找用户
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfo getByUserId(Long userId) {
        return super.getById(userId);
    }

    /**
     * 修改用户邮箱
     *
     * @param newEmail
     * @param userName
     * @return
     */
    @Override
    public boolean updateEmail(String newEmail, String userName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserEmail(newEmail);
        return super.update(userInfo, new UpdateWrapper<UserInfo>().eq("user_name",userName));
    }

    /**
     * 根据用户id更新用户信息
     *
     * @param userInfo
     * @param userId
     */
    @Override
    public boolean update(UserInfo userInfo, Long userId) {
        return super.update(userInfo, new UpdateWrapper<UserInfo>().eq("user_id", userId));
    }

    /**
     * 查看用户分页列表
     * @param pageP
     * @param keyword
     * @return
     */
    @Override
    public IPage<UserInfoDTO> getUserInfoListPage(Page pageP, String keyword) {
        return userInfoMapper.selectUserInfoVOListPage(pageP, keyword);
    }

    /**
     * 根据用户id获取用户
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfoDTO getUserDtoByUserId(Long userId) {
        return userInfoMapper.selectUserDtoByUserId(userId);
    }

    /**
     * 根据用户id修改用户
     *
     * @param userInfo
     * @return
     */
    @Override
    public boolean updateByUserId(UserInfo userInfo) {
        return super.update(userInfo, new UpdateWrapper<UserInfo>().eq("user_id",userInfo.getUserId()));
    }

    /**
     * 获取账户是否已经被禁用
     * @param username
     * @return
     */
    @Override
    public Boolean getIsDisabledByUserName(String username) {
        return userInfoMapper.selectIsDisabled(username);
    }
}
