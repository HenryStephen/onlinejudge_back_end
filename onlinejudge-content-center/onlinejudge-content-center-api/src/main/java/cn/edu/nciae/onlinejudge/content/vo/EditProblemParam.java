package cn.edu.nciae.onlinejudge.content.vo;

import cn.edu.nciae.onlinejudge.content.domain.Problem;
import cn.edu.nciae.onlinejudge.content.domain.Sample;
import lombok.*;

import java.util.List;

/**
 * @author zhanghonglin
 * @date 2021/5/28
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EditProblemParam extends Problem {
	/**
	 * 展示id
	 */
	private Long problemDisplayId;

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
	private List<String> tags;

	/**
	 * Languages of the problem
	 */
	private List<String> languages;
}
