package cn.edu.nciae.onlinejudge.judge.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/5 22:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionVO implements Serializable {

    /**
     * 评测所需参数
     */
    private JudgeVO judgeVO;

    /**
     * submissionId
     */
    private Long submissionId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 问题id
     */
    private Long problemId;

    /**
     * 竞赛id
     */
    private Long contestId;

    /**
     * 编程语言id
     */
    private Integer languageId;


}
