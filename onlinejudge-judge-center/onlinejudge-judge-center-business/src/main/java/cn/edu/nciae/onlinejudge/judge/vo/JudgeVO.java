package cn.edu.nciae.onlinejudge.judge.vo;

import cn.edu.nciae.onlinejudge.judge.domain.Compile;
import cn.edu.nciae.onlinejudge.judge.domain.Run;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/30 21:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JudgeVO implements Serializable {

    /**
     * 源码
     */
    String src;

    /**
     * 语言配置
     */
    LanguageConfig language_config;

    /**
     * 时间限制
     */
    Integer max_cpu_time;

    /**
     * 内存限制
     */
    Integer max_memory;

    /**
     * 测试用例id
     */
    String test_case_id;

    /**
     * 是否输出 用户的输出结果
     */
    boolean output;

}
