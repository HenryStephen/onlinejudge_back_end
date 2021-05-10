package cn.edu.nciae.onlinejudge.judge.vo;

import lombok.*;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/21 16:53
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartBeatParam {

    /**
     * 评测机版本
     */
    private String judger_version;

    /**
     * 主机名
     */
    private String hostname;

    /**
     * 正在运行任务的数量
     */
    private Integer running_task_number;

    /**
     * CPU核心数
     */
    private Integer cpu_core;

    /**
     * 内存大小
     */
    private Double memory;

    /**
     * 动作类型
     */
    private String action;

    /**
     * CPU
     */
    private Integer cpu;

    /**
     * judge_server url
     */
    private String service_url;

}
