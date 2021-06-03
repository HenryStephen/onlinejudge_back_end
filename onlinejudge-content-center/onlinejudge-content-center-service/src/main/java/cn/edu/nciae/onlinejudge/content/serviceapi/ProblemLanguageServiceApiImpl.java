package cn.edu.nciae.onlinejudge.content.serviceapi;

import cn.edu.nciae.onlinejudge.content.api.ProblemLanguageServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.ProblemLanguage;
import cn.edu.nciae.onlinejudge.content.service.impl.ProblemLanguageServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 23768
 * @date 2021/5/29
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class ProblemLanguageServiceApiImpl extends ProblemLanguageServiceImpl implements ProblemLanguageServiceApi {

	/**
	 * 添加题目语言关联
	 * @param problemLanguage
	 * @return
	 */
	@Override
	public boolean save(ProblemLanguage problemLanguage){
		return super.save(problemLanguage);
	}

	/**
	 * 根据题目id删除关联关系
	 * @param problemId
	 * @return
	 */
	@Override
	public boolean removeByProblemId(Long problemId) {
		return super.remove(new QueryWrapper<ProblemLanguage>().eq("problem_id",problemId));
	}
}
