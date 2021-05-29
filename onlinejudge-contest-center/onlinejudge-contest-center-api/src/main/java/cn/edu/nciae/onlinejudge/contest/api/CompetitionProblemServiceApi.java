package cn.edu.nciae.onlinejudge.contest.api;

import cn.edu.nciae.onlinejudge.contest.domain.CompetitionProblem;
import cn.edu.nciae.onlinejudge.contest.vo.CompetitionProblemDTO;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 16:39
 */
public interface CompetitionProblemServiceApi {

    /**
     * 根据竞赛id查看题目列表
     * @param competitionId
     * @return
     */
    List<CompetitionProblemDTO> list(Long competitionId);

    /**
     * 根据竞赛id和显示id查看题目id
     * @param competitionId
     * @param problemDisplayId
     * @return
     */
    CompetitionProblem getByCompetitionIdAndDisplayId(Long competitionId, Long problemDisplayId);

	/**
	 * 根据题目id查看CompetitionProblem
	 * @param problemId
	 * @return
	 */
	CompetitionProblem getByCompetitionIdAndProblemId(Long competitionId, Long problemId);

	/**
	 * 添加竞赛题目关系
	 * @param competitionProblem
	 * @return
	 */
	boolean save(CompetitionProblem competitionProblem);
}
