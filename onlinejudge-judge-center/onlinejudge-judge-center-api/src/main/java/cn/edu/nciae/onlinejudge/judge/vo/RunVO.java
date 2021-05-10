package cn.edu.nciae.onlinejudge.judge.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/1 18:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RunVO implements Serializable {
    /**
     * 运行时配置的id
     */
    private Integer run_id;

    /**
     * 配置的名称
     */
    private String config_name;

    /**
     *
     */
    private String command;

    /**
     *
     */
    private String seccomp_rule;

    /**
     *
     */
    private String exe_name;

    /**
     *
     */
    private String[] env;

    /**
     *
     */
    private Integer memory_limit_check_only;


    private static final long serialVersionUID = -3537478646255030568L;
}
