package cn.edu.nciae.onlinejudge.content.api;

import cn.edu.nciae.onlinejudge.content.domain.ProblemTag;

/**
 * @author 23768
 * @date 2021/5/30
 */
public interface ProblemTagServiceApi {

	/**
	 * 添加ProblemTag
	 * @param problemTag
	 * @return
	 */
	boolean save(ProblemTag problemTag);

	/**
	 * 根据problemid删除所有的problem_language
	 * @param problemId
	 * @return
	 */
	boolean removeByProblemId(Long problemId);
}
