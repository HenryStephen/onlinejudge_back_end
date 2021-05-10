package cn.edu.nciae.onlinejudge.contest.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/8 9:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionListVO {

    /**
     * Result set
     */
    private List<CompetitionDTO> results;

    /**
     * Total Problem Num
     */
    private Long total;
}
