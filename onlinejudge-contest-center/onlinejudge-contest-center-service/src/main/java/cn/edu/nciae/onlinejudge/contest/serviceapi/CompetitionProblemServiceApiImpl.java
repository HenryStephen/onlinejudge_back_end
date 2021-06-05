package cn.edu.nciae.onlinejudge.contest.serviceapi;

import cn.edu.nciae.onlinejudge.contest.api.CompetitionProblemServiceApi;
import cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem;
import cn.edu.nciae.onlinejudge.contest.mapper.CompetitionProblemMapper;
import cn.edu.nciae.onlinejudge.contest.service.impl.CompetitionProblemServiceImpl;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionProblemDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    public List<CompetitionProblemDTO> listByCompetitionId(Long competitionId) {
        return competitionProblemMapper.selectListById(competitionId);
    }

    /**
     * 根据竞赛id和赛制查看题目列表
     *
     * @param competitionId
     * @param problemRuleType
     * @return
     */
    @Override
    public List<CompetitionProblemDTO> listByCompetitionIdAndRuleType(Long competitionId, String problemRuleType) {
        return competitionProblemMapper.selectListByIdAndRuleType(competitionId, problemRuleType);
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

    /**
     * 添加竞赛题目关系
     * @param competitionProblem
     * @return
     */
    @Override
    public boolean save(CompetitionProblem competitionProblem) {
        return super.save(competitionProblem);
    }

    /**
     * 根据竞赛id和题目id更新关联关系
     *
     * @param competitionProblem
     * @return
     */
    @Override
    public boolean updateByCompetitionIdAndProblemId(CompetitionProblem competitionProblem) {
        return super.update(competitionProblem, new UpdateWrapper<CompetitionProblem>()
                .eq("competition_id",competitionProblem.getCompetitionId())
                .eq("problem_id",competitionProblem.getProblemId()));
    }

    /**
     * 根据竞赛id和题目id逻辑删除题目
     * @param competitionId
     * @param problemId
     * @return
     */
    @Override
    public boolean removeByCompetitionIdAndProblemId(long competitionId, Long problemId) {
        return super.remove(new QueryWrapper<CompetitionProblem>()
                .eq("competition_id",competitionId)
                .eq("problem_id",problemId));
    }

    /**
     * 获取某个竞赛或者公共题目中最大的展示id
     * @param competitionId
     * @return
     */
    @Override
    public Long getMaxDisplayId(Long competitionId) {
        return competitionProblemMapper.selectMaxDisplayId(competitionId);
    }

}
