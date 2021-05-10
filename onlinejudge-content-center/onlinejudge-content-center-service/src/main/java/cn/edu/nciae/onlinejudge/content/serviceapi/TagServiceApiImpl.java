package cn.edu.nciae.onlinejudge.content.serviceapi;

import cn.edu.nciae.onlinejudge.content.api.TagServiceApi;
import cn.edu.nciae.onlinejudge.content.domain.Tag;
import cn.edu.nciae.onlinejudge.content.service.impl.TagServiceImpl;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 14:57
 */

@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class TagServiceApiImpl extends TagServiceImpl implements TagServiceApi {

    /**
     * 查询标签列表
     * @return
     */
    @Override
    public List<Tag> list() {
        return super.list();
    }
}
