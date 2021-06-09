package cn.edu.nciae.onlinejudge.statistic.api;


import cn.edu.nciae.onlinejudge.statistic.domain.UserProblem;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/6 16:14
 */
public interface UserProblemServiceApi {

    /**
     * 根据用户id和问题id获取status
     * @param userId
     * @param displayId
     * @param competitionId
     * @return
     */
    UserProblem getStatusByUserIdAndDisplayIdAndCompetitionId(Long userId, Long displayId, Long competitionId);

    /**
     * 获取用户已经解决的问题列表
     * @param userId
     * @param competitionId
     * @return
     */
    List<Long> getSolvedProblemIdList(Long userId, Long competitionId);

    /**
     * 添加或者修改userproblem
     * @param userProblem
     */
    boolean saveOrUpdate(UserProblem userProblem);
}
