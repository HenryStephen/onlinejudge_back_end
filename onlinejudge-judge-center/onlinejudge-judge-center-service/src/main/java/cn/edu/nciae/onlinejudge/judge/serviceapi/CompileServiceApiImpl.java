package cn.edu.nciae.onlinejudge.judge.serviceapi;

import cn.edu.nciae.onlinejudge.judge.api.CompileServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Compile;
import cn.edu.nciae.onlinejudge.judge.mapper.CompileMapper;
import cn.edu.nciae.onlinejudge.judge.service.impl.CompileServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/1 17:00
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class CompileServiceApiImpl extends CompileServiceImpl implements CompileServiceApi {

    @Autowired
    private CompileMapper compileMapper;

    /**
     * 根据编译配置id查询编译配置信息
     *
     * @param languageCompileId
     * @return
     */
    @Override
    public Compile getCompileById(Integer languageCompileId) {
        return compileMapper.selectByCompileId(languageCompileId);
    }
}
