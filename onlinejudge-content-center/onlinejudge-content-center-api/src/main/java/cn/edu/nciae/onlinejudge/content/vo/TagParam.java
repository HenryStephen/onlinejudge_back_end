package cn.edu.nciae.onlinejudge.content.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @date 2021/5/28
 */
/**
 * 标签查询参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TagParam implements Serializable {
	private String keyword;
}
