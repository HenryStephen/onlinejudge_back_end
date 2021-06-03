package cn.edu.nciae.onlinejudge.content.api;

import cn.edu.nciae.onlinejudge.content.domain.ProblemLanguage;

/**
 * @author 23768
 * @date 2021/5/29
 */
public interface ProblemLanguageServiceApi {

	/**
	 * 添加题目语言关联
	 * @param problemLanguage
	 * @return
	 */
	boolean save(ProblemLanguage problemLanguage);

	/**
	 * 根据题目id删除关联关系
	 * @param problemId
	 * @return
	 */
	boolean removeByProblemId(Long problemId);
}
