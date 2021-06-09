package cn.edu.nciae.onlinejudge.statistic.serviceapi;


import cn.edu.nciae.onlinejudge.statistic.api.UserProblemServiceApi;
import cn.edu.nciae.onlinejudge.statistic.domain.UserProblem;
import cn.edu.nciae.onlinejudge.statistic.mapper.UserProblemMapper;
import cn.edu.nciae.onlinejudge.statistic.service.impl.UserProblemServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/6 16:14
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class UserProblemServiceApiImpl extends UserProblemServiceImpl implements UserProblemServiceApi {

    @Autowired
    private UserProblemMapper userProblemMapper;

    /**
     * 根据用户id和问题id获取status
     *
     * @param userId
     * @param displayId
     * @param competitionId
     * @return
     */
    @Override
    public UserProblem getStatusByUserIdAndDisplayIdAndCompetitionId(Long userId, Long displayId, Long competitionId) {
        return super.getOne(new QueryWrapper<UserProblem>()
                .eq("user_id",userId)
                .eq("competition_id",competitionId)
                .eq("problem_display_id",displayId));
    }

    /**
     * 获取用户已经解决的问题列表
     *
     * @param userId
     * @param competitionId
     * @return
     */
    @Override
    public List<Long> getSolvedProblemIdList(Long userId, Long competitionId) {
        return userProblemMapper.selectSolvedProblem(userId, competitionId);
    }

    /**
     * 添加或者修改userproblem
     * @param userProblem
     * @return
     */
    @Override
    public boolean saveOrUpdate(UserProblem userProblem){
        return super.saveOrUpdate(userProblem);
    }
}
