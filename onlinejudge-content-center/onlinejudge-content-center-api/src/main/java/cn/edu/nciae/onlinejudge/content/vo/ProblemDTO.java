package cn.edu.nciae.onlinejudge.content.vo;

import cn.edu.nciae.onlinejudge.commons.utils.VOUtils;
import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.domain.Sample;
import cn.edu.nciae.onlinejudge.content.domain.Tag;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "problem", resultMap = "ProblemViewResultMap")
public class ProblemDTO extends Problem {

    /**
     * 展示id
     */
    private Long problemDisplayId;

    /**
     * 题目所占分数
     */
    private Long problemScore;

    /**
     * 提交人数
     */
    private Integer submitNumber;

    /**
     * 通过人数
     */
    private Integer solvedNumber;

	/**
	 * 竞赛id
	 */
	private Long contestId;

    /**
     * Sample input and output
     */
    private List<Sample> samples;

    /**
     * Tags of the problem
     */
    private List<Tag> tags;

    /**
     * Languages of the problem
     */
    private List<String> languages;

    /**
     * 用户对该题的状态
     */
    private Integer myStatus;

    /**
     * desc : get Problem instance from VO
     *
     * @return Problem
     */
    public Problem unzipProblemVO() {
        return VOUtils.getSuperObjectFromSubObject(this, Problem.class);
    }

    /**
     * desc : zip Problem instance to VO
     */
    public void zipProblem(Problem problem) {
        BeanUtils.copyProperties(problem, this);
    }

    @Override
    public String toString() {
        String problemString = unzipProblemVO().toString();
        String sampleString = "";
        for (Sample s : this.samples) {
            sampleString = sampleString.concat(s.toString());
        }
        return problemString + "\n" + sampleString;
    }
}
