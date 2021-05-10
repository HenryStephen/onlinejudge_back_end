package cn.edu.nciae.onlinejudge.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import cn.edu.nciae.onlinejudge.user.service.UserInfoService;
import cn.edu.nciae.onlinejudge.user.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
implements UserInfoService{

}




