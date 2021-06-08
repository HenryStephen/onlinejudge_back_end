package cn.edu.nciae.onlinejudge.contest.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 9:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompetitionParam implements Serializable {

    /**
     * 赛制
     */
    private String rule_type;

    /**
     * 比赛状态
     */
    private Integer status;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 是否管理员
     */
    private Boolean isAdmin;

    //附加信息：创建比赛的id
    private Long createUserId;
}
