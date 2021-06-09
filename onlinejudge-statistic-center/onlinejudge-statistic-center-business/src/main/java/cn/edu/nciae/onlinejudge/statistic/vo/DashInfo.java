package cn.edu.nciae.onlinejudge.statistic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashInfo {

    /**
     * 用户数量
     */
    private Integer userCount;

    /**
     * 最近比赛个数
     */
    private Integer recentContestCount;

    /**
     * 所有的提交数量
     */
    private Integer totalSubmissionCount;
}
