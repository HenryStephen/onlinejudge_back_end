package cn.edu.nciae.onlinejudge.judge.api;

import cn.edu.nciae.onlinejudge.judge.domain.Compile;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/1 16:58
 */
public interface CompileServiceApi {

    /**
     * 根据编译配置id查询编译配置信息
     * @param languageCompileId
     * @return
     */
    Compile getCompileById(Integer languageCompileId);
}
