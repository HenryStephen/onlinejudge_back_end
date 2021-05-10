package cn.edu.nciae.onlinejudge.judge.serviceapi;

import cn.edu.nciae.onlinejudge.judge.api.LanguagesServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Languages;
import cn.edu.nciae.onlinejudge.judge.mapper.LanguagesMapper;
import cn.edu.nciae.onlinejudge.judge.service.impl.LanguagesServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 14:23
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class LanguagesServiceApiImpl extends LanguagesServiceImpl implements LanguagesServiceApi {

    @Autowired
    private LanguagesMapper languagesMapper;

    /**
     * 查询编程语言列表
     * @return
     */
    @Override
    public List<Languages> list() {
        return super.list();
    }

    /**
     * 根据语言名字查询语言
     *
     * @param languageName
     * @param isSpj
     * @return
     */
    @Override
    public Languages getLanguageByLanguageName(String languageName, Boolean isSpj) {
        return super.getOne(new QueryWrapper<Languages>()
                .eq("language_name",languageName)
                .eq("is_spj",isSpj));
    }

    /**
     * 根据id查找编程语言
     *
     * @param languageId
     * @return
     */
    @Override
    public Languages getLanguageById(Integer languageId) {
        return super.getById(languageId);
    }
}
