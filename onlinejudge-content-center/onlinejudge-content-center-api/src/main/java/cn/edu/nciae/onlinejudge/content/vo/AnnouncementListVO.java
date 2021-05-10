package cn.edu.nciae.onlinejudge.content.vo;

import cn.edu.nciae.onlinejudge.content.domain.Announcement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/26 21:04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementListVO {
    /**
     * Result set
     */
    private List<Announcement> results;

    /**
     * Total Problem Num
     */
    private Long total;
}
