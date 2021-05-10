package cn.edu.nciae.onlinejudge.content.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Checkpoint implements Serializable {

    /**
     * 检查点ID
     */
    private Integer checkpointId;

    /**
     * 题目ID与内容中心同步
     */
    private Long problemId;

    /**
     * 标准输入
     */
    private String input;

    /**
     * 标准输出
     */
    private String output;


    private static final long serialVersionUID = 6587525817912094572L;

}
