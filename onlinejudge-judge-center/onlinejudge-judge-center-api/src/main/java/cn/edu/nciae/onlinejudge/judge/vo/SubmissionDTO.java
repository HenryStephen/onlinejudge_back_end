package cn.edu.nciae.onlinejudge.judge.vo;

import cn.edu.nciae.onlinejudge.judge.domain.Submission;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/7 10:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "submission", resultMap = "SubmissionViewResultMap")
public class SubmissionDTO extends Submission {
    /**
     * 作者
     */
    private String username;

    /**
     * 编程语言名称
     */
    private String language;
}
