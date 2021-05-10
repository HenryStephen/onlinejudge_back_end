package cn.edu.nciae.onlinejudge.user.serviceapi;

import cn.edu.nciae.onlinejudge.user.api.PermissionServiceApi;
import cn.edu.nciae.onlinejudge.user.domain.Permission;
import cn.edu.nciae.onlinejudge.user.mapper.PermissionMapper;
import cn.edu.nciae.onlinejudge.user.service.impl.PermissionServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/17 19:50
 */
@Service
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class PermissionServiceApiImpl extends PermissionServiceImpl implements PermissionServiceApi {

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 根据用户id查找对应权限
     * @param userId
     * @return
     */
    @Override
    public List<Permission> selectPermissionByUserId(Long userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }
}
