package cn.edu.nciae.onlinejudge.judge.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/5 23:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JudgeResponse implements Serializable {

    /**
     * cpu 时间
     */
    private Integer cpu_time;

    /**
     * 结果代码
     */
    private Integer result;

    /**
     * 内存
     */
    private Integer memory;

    /**
     * 真实时间
     */
    private Integer real_time;

    /**
     * 标志
     */
    private Integer signal;

    /**
     * 错误代码
     */
    private Integer error;

    /**
     * 结束代码
     */
    private Integer exit_code;

    /**
     * 输出信息的md5
     */
    private String output_md5;

    /**
     * 第几个测试用例
     */
    private Integer test_case;

    /**
     * 输出信息
     */
    private String output;
}
