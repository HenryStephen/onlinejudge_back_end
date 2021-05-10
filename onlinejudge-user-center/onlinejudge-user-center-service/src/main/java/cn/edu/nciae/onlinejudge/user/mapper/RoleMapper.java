package cn.edu.nciae.onlinejudge.user.mapper;

import cn.edu.nciae.onlinejudge.user.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity cn.edu.nciae.onlinejudge.user.domain.Role
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查找对应角色
     *
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(Long userId);
}




