package cn.edu.nciae.onlinejudge.judge.serviceapi;

import cn.edu.nciae.onlinejudge.judge.api.RunServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Run;
import cn.edu.nciae.onlinejudge.judge.mapper.RunMapper;
import cn.edu.nciae.onlinejudge.judge.service.impl.RunServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/30 23:29
 */

@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class RunServiceApiImpl extends RunServiceImpl implements RunServiceApi {

    @Autowired
    private RunMapper runMapper;

    /**
     * 根据id获取运行参数
     *
     * @param languageRunId
     * @return
     */
    @Override
    public Run getRunById(Integer languageRunId) {
        return runMapper.selectByRunId(languageRunId);
    }
}
