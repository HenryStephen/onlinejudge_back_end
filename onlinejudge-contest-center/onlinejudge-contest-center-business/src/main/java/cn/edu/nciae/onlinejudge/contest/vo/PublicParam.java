package cn.edu.nciae.onlinejudge.contest.vo;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublicParam implements Serializable {

    /**
     * 竞赛id
     */
    private Long competitionId;

    /**
     * 题目id
     */
    private Long problemId;

    /**
     * 展示id
     */
    private Long problemDisplayId;

}
