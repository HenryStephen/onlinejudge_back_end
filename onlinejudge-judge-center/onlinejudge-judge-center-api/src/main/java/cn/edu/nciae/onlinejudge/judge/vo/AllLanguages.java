package cn.edu.nciae.onlinejudge.judge.vo;

import cn.edu.nciae.onlinejudge.judge.domain.Languages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 23768
 * @date 2021/5/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllLanguages implements Serializable {
	/**
	 * 编程语言
	 */
	private List<Languages> languages;

	/**
	 * 特殊判题编程语言
	 */
	private List<Languages> spj_languages;
}
