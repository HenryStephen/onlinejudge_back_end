package cn.edu.nciae.onlinejudge.statistic.mapper;

import cn.edu.nciae.onlinejudge.statistic.domain.UserProblem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity cn.edu.nciae.onlinejudge.statistic.domain.UserProblem
 */
public interface UserProblemMapper extends BaseMapper<UserProblem> {

    /**
     * 获取用户解决问题的列表
     * @param userId
     * @return
     */
    List<Long> selectSolvedProblem(Long userId);
}




