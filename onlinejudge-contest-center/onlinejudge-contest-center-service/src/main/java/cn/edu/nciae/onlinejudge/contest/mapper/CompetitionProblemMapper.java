package cn.edu.nciae.onlinejudge.contest.mapper;

import cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionProblemDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem
 */
public interface CompetitionProblemMapper extends BaseMapper<CompetitionProblem> {

    /**
     * 根据竞赛id查看题目列表
     * @param competitionId
     * @return
     */
    List<CompetitionProblemDTO> selectListById(Long competitionId);

    /**
     * 获取某个竞赛或者公共题目中最大的展示id
     * @param competitionId
     * @return
     */
    Long selectMaxDisplayId(Long competitionId);

    /**
     * 根据竞赛id和赛制查看题目列表
     *
     * @param competitionId
     * @param problemRuleType
     * @return
     */
    List<CompetitionProblemDTO> selectListByIdAndRuleType(Long competitionId, String problemRuleType);
}




