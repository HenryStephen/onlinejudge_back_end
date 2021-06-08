package cn.edu.nciae.onlinejudge.content.vo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:07
 */

/**
 * 题目查询参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProblemParam implements Serializable {

    private static final long serialVersionUID = -7745255852064700219L;
    private String keyword;
    private String difficulty;
    private String tag;
    private Integer page;
    private Long contestId;
    private String problemRuleType;

    /**
     * 查出对应竞赛（公共的也可以）的题目id列表
     */
    private List<Long> problemIdList;

    /**
     * 添加题目者id
     */
    private Long addUserId;
}
