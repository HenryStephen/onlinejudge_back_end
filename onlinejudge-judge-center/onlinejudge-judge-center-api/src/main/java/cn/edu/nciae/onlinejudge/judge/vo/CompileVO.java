package cn.edu.nciae.onlinejudge.judge.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/1 18:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompileVO implements Serializable {
    /**
     * 编译配置的id
     */
    private Integer compile_id;

    /**
     * 配置的名称
     */
    private String config_name;

    /**
     *
     */
    private String src_name;

    /**
     *
     */
    private String exe_name;

    /**
     *
     */
    private Long max_cpu_time;

    /**
     *
     */
    private Long max_real_time;

    /**
     *
     */
    private Long max_memory;

    /**
     * 编译命令
     */
    private String compile_command;

    /**
     * 环境变量
     */
    private String env;

    private static final long serialVersionUID = -1878680911152850866L;
}
