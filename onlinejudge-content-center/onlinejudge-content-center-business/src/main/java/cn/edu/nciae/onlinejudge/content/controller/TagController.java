package cn.edu.nciae.onlinejudge.content.controller;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;
import cn.edu.nciae.onlinejudge.commons.dto.ResponseResult;
import cn.edu.nciae.onlinejudge.content.api.TagServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Tag;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 14:52
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Reference(version = "1.0.0", check = false)
    private TagServiceApi tagServiceApi;

    /**
     * 查询标签列表
     * @return
     */
    @GetMapping
    public ResponseResult<List<Tag>> getTagList() {
        List<Tag> tagList = tagServiceApi.list();
        return ResponseResult.<List<Tag>>builder()
                .code(BusinessStatus.OK.getCode())
                .message("查询标签列表成功")
                .data(tagList)
                .build();
    }
}
