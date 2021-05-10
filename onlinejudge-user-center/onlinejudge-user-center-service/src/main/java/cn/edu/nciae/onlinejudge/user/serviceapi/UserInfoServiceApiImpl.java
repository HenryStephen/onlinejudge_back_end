package cn.edu.nciae.onlinejudge.user.serviceapi;

import cn.edu.nciae.onlinejudge.user.api.UserInfoServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.mapper.UserInfoMapper;
import cn.edu.nciae.onlinejudge.user.service.UserInfoService;
import cn.edu.nciae.onlinejudge.user.service.impl.UserInfoServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
