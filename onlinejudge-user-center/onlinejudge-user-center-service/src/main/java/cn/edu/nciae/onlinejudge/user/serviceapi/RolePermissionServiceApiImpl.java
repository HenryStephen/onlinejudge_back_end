package cn.edu.nciae.onlinejudge.user.serviceapi;

import cn.edu.nciae.onlinejudge.user.api.RolePermissionServiceApi;
import cn.edu.nciae.onlinejudge.user.service.impl.RolePermissionServiceImpl;
import org.springframework.stereotype.Service;

@Service
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class RolePermissionServiceApiImpl extends RolePermissionServiceImpl implements RolePermissionServiceApi {

}
