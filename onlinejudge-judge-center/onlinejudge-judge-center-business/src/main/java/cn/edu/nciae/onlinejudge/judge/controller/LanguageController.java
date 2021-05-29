package cn.edu.nciae.onlinejudge.judge.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.judge.api.LanguagesServiceApi;
import cn.edu.nciae.onlinejudge.judge.domain.Languages;
import cn.edu.nciae.onlinejudge.judge.vo.AllLanguages;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 14:17
 */
@RestController
@RequestMapping("/judge/language")
public class LanguageController {

    @Reference(version = "1.0.0", check = false)
    private LanguagesServiceApi languagesServiceApi;

    /**
     * 查询编程语言列表
     * @return
     */
    @GetMapping
    public ResponseResult<List<Languages>> getLanguageList() {
        List<Languages> languages = languagesServiceApi.list();
        return ResponseResult.<List<Languages>>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询编程语言列表成功")
                .data(languages)
                .build();
    }

    /**
     * 查询编程语言列表（管理员）
     * @return
     */
    @GetMapping("/admin")
    public ResponseResult<AllLanguages> getLanguageListAdmin(){
        List<Languages> languages = languagesServiceApi.listByNoSpj();
        List<Languages> spj_languages = languagesServiceApi.listBySpj();
        return ResponseResult.<AllLanguages>builder()
                .data(AllLanguages.builder().languages(languages).spj_languages(spj_languages).build())
                .code(BusinessStatus.OK.getCode())
                .message("查询编程语言列表成功")
                .build();
    }
}
