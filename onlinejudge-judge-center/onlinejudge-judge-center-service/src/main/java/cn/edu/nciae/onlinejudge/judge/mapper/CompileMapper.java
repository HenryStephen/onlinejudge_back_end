package cn.edu.nciae.onlinejudge.judge.mapper;

import cn.edu.nciae.onlinejudge.judge.domain.Compile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity cn.edu.nciae.onlinejudge.judge.domain.Compile
 */
public interface CompileMapper extends BaseMapper<Compile> {

    /**
     * 根据id查询编译配置
     * @param compileId
     * @return
     */
    Compile selectByCompileId(Integer compileId);

}




