package cn.edu.nciae.onlinejudge.judge.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/7 10:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionListVO {

    /**
     * Result set
     */
    private List<SubmissionDTO> results;

    /**
     * Total Problem Num
     */
    private Long total;

}
