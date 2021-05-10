package cn.edu.nciae.onlinejudge.judge.mapper;

import cn.edu.nciae.onlinejudge.judge.domain.Run;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity cn.edu.nciae.onlinejudge.judge.domain.Run
 */
public interface RunMapper extends BaseMapper<Run> {

    /**
     * 根据id查找run
     * @param runId
     * @return
     */
    Run selectByRunId(Integer runId);
}




