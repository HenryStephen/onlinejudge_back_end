package cn.edu.nciae.onlinejudge.content.vo;

import lombok.*;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:06
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemListVO{

    /**
     * Result set
     */
    private List<ProblemDTO> results;

    /**
     * Total Problem Num
     */
    private Long total;

}

