package cn.edu.nciae.onlinejudge.content.api;

import cn.edu.nciae.onlinejudge.content.domain.Tag;
import cn.edu.nciae.onlinejudge.content.vo.TagParam;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 14:50
 */
public interface TagServiceApi {

    /**
     * 根据参数查询标签列表
     * @return
     */
    List<Tag> listByParam(TagParam tagParam);

    /**
     * 获取标签列表
     * @return
     */
    List<Tag> list();

    /**
     * 根据标签名称查找标签
     * @param tagName
     * @return
     */
    Tag getTagByTagName(String tagName);

    /**
     * 添加标签
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);
}
