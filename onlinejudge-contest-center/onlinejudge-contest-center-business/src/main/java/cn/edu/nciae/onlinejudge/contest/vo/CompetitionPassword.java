package cn.edu.nciae.onlinejudge.contest.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 15:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompetitionPassword implements Serializable {
    /**
     * 竞赛id
     */
    private Long competitionId;

    /**
     * 密码
     */
    private String password;
}
