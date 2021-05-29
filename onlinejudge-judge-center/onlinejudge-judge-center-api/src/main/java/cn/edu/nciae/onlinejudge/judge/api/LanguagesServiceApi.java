package cn.edu.nciae.onlinejudge.judge.api;


import cn.edu.nciae.onlinejudge.judge.domain.Languages;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 14:20
 */
public interface LanguagesServiceApi {

    /**
     * 查询编程语言列表
     * @return
     */
    List<Languages> list();

    /**
     * 根据语言名字查询语言
     * @param languageName
     * @param isSpj
     * @return
     */
    Languages getLanguageByLanguageName(String languageName, Boolean isSpj);

    /**
     * 根据id查找编程语言
     * @param languageId
     * @return
     */
    Languages getLanguageById(Integer languageId);

    /**
     * 查找出不是特殊判题的编程语言
     * @return
     */
	List<Languages> listByNoSpj();

    /**
     * 查找出特殊判题的编程语言
     * @return
     */
    List<Languages> listBySpj();
}
