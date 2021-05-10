package cn.edu.nciae.onlinejudge.judge.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/30 20:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionParam implements Serializable {

    /**
     * 题目id
     */
    private Long problem_id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 代码code
     */
    private String code;

    /**
     * 竞赛id
     */
    private Long contest_id;

}
