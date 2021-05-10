package cn.edu.nciae.onlinejudge.user.mapper;

import cn.edu.nciae.onlinejudge.user.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity cn.edu.nciae.onlinejudge.user.domain.Permission
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户id查找对应权限
     * @param userId
     * @return
     */
    List<Permission> selectPermissionByUserId(@Param("userId") Long userId);

}




