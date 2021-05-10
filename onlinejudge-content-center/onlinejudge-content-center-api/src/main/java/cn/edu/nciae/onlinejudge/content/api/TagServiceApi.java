package cn.edu.nciae.onlinejudge.content.api;

import cn.edu.nciae.onlinejudge.content.domain.Tag;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 14:50
 */
public interface TagServiceApi {

    /**
     * 查询标签列表
     * @return
     */
    List<Tag> list();
}
