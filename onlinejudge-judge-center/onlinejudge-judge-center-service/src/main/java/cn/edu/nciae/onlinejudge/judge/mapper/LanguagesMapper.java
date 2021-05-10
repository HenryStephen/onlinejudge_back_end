package cn.edu.nciae.onlinejudge.judge.mapper;

import cn.edu.nciae.onlinejudge.judge.domain.Languages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity cn.edu.nciae.onlinejudge.judge.domain.Languages
 */
public interface LanguagesMapper extends BaseMapper<Languages> {

    /**
     * 根据id查找语言
     * @param languageId
     * @return
     */
    String getLanguageNameByLanguageId(Integer languageId);
}




