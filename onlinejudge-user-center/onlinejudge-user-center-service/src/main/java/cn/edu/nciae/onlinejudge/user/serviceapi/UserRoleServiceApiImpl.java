package cn.edu.nciae.onlinejudge.user.serviceapi;

import cn.edu.nciae.onlinejudge.user.api.UserRoleServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.UserRole;
import cn.edu.nciae.onlinejudge.user.service.impl.UserRoleServiceImpl;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;

@Service
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class UserRoleServiceApiImpl extends UserRoleServiceImpl implements UserRoleServiceApi {
    /**
     * 根据用户id更新用户的角色信息
     *
     * @param userRole
     * @return
     */
    @Override
    public boolean updateByUserId(UserRole userRole) {
        return super.update(userRole, new UpdateWrapper<UserRole>().eq("user_id",userRole.getUserId()));
    }

    /**
     * 添加user-role的关系
     * @param userRole
     * @return
     */
    @Override
    public boolean save(UserRole userRole){
        return super.save(userRole);
    }
}
