package cn.edu.nciae.onlinejudge.judge.serviceapi;

import cn.edu.nciae.onlinejudge.judge.api.CheckpointServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Checkpoint;
import cn.edu.nciae.onlinejudge.judge.service.impl.CheckpointServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/25 22:23
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class CheckpointServiceApiImpl extends CheckpointServiceImpl implements CheckpointServiceApi {

    /**
     * 添加测试用例
     * @param checkpoint
     * @return
     */
    @Override
    public boolean save(Checkpoint checkpoint){
        return super.save(checkpoint);
    }
}
