package cn.edu.nciae.onlinejudge.content.serviceapi;

import cn.edu.nciae.onlinejudge.content.api.SampleServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Sample;
import cn.edu.nciae.onlinejudge.content.service.impl.SampleServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/25 14:49
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class SampleServiceApiImpl extends SampleServiceImpl implements SampleServiceApi {

    /**
     * 根据题目id删除样例输入输出
     *
     * @param problemId
     * @return
     */
    @Override
    public boolean removeByProblemId(Long problemId) {
        return super.remove(new QueryWrapper<Sample>().eq("problem_id",problemId));
    }
}
