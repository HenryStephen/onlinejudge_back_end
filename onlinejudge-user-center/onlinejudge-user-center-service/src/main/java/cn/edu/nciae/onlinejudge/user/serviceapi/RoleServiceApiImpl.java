package cn.edu.nciae.onlinejudge.user.serviceapi;

import cn.edu.nciae.onlinejudge.user.api.RoleServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.Role;
import cn.edu.nciae.onlinejudge.user.mapper.RoleMapper;
import cn.edu.nciae.onlinejudge.user.service.impl.RoleServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/27 17:17
 */
@Service
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class RoleServiceApiImpl extends RoleServiceImpl implements RoleServiceApi {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 根据用户id查找对应角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> selectRoleByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    /**
     * 根据角色名字获取角色
     * @param roleType
     * @return
     */
    @Override
    public Role getRoleByRoleName(String roleType) {
        return super.getOne(new QueryWrapper<Role>().eq("enname", roleType));
    }
}
