package cn.edu.nciae.onlinejudge.content.serviceapi;

import cn.edu.nciae.onlinejudge.content.api.ProblemTagServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.ProblemTag;
import cn.edu.nciae.onlinejudge.content.service.impl.ProblemTagServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 23768
 * @date 2021/5/30
 */

@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class ProblemTagServiceApiImpl extends ProblemTagServiceImpl implements ProblemTagServiceApi {

	/**
	 * 添加ProblemTag
	 * @param problemTag
	 * @return
	 */
	@Override
	public boolean save(ProblemTag problemTag){
		return super.save(problemTag);
	}
}
