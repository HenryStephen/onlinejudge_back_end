package cn.edu.nciae.onlinejudge.judge.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/1 19:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LanguageConfig {

    /**
     * 运行信息
     */
    private RunVO run;

    /**
     * 编译信息
     */
    private CompileVO compile;
}
