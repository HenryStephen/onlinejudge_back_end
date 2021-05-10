package cn.edu.nciae.onlinejudge.judge.message.consumer;

import cn.edu.nciae.onlinejudge.commons.utils.MapperUtils;
import cn.edu.nciae.onlinejudge.judge.api.CheckpointServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Checkpoint;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/25 21:02
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class CheckpointConsumer {

    @Reference(version = "1.0.0",check = false)
    private CheckpointServiceApi checkpointServiceApi;

    /**
     * 增加测试用例
     * @param checkpointListJson
     * @throws Exception
     */
    @StreamListener("add-checkpoint-input")
    public void addCheckpoint(String checkpointListJson) throws Exception {
        List<Checkpoint> checkpointList = MapperUtils.json2list(checkpointListJson, Checkpoint.class);
        for (Checkpoint checkpoint : checkpointList) {
            checkpointServiceApi.save(checkpoint);
        }
    }
}
