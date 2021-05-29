package cn.edu.nciae.onlinejudge.contest.serviceapi;

import cn.edu.nciae.onlinejudge.contest.api.CompetitionProblemServiceApi;
import cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem;
import cn.edu.nciae.onlinejudge.contest.mapper.CompetitionProblemMapper;
import cn.edu.nciae.onlinejudge.contest.service.impl.CompetitionProblemServiceImpl;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionProblemDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 16:39
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class CompetitionProblemServiceApiImpl extends CompetitionProblemServiceImpl implements CompetitionProblemServiceApi {

    @Autowired
    private CompetitionProblemMapper competitionProblemMapper;

    /**
     * 根据竞赛id查看题目列表
     *
     * @param competitionId
     * @return
     */
    @Override
    public List<CompetitionProblemDTO> list(Long competitionId) {
        return competitionProblemMapper.selectListById(competitionId);
    }

    /**
     * 根据竞赛id和显示id查看题目id
     *
     * @param competitionId
     * @param problemDisplayId
     * @return
     */
    @Override
    public CompetitionProblem getByCompetitionIdAndDisplayId(Long competitionId, Long problemDisplayId) {
        return super.getOne(new QueryWrapper<CompetitionProblem>().eq("competition_id",competitionId).eq("problem_display_id",problemDisplayId));
    }

    /**
     * 根据题目id查看CompetitionProblem
     *
     * @param competitionId
     * @param problemId
     * @return
     */
    @Override
    public CompetitionProblem getByCompetitionIdAndProblemId(Long competitionId, Long problemId) {
        return super.getOne(new QueryWrapper<CompetitionProblem>().eq("competition_id",competitionId).eq("problem_id", problemId));
    }
}
