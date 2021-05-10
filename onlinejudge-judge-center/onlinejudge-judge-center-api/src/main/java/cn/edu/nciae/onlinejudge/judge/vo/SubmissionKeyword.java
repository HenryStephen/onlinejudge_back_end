package cn.edu.nciae.onlinejudge.judge.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/6 20:41
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionKeyword implements Serializable {

    private static final long serialVersionUID = 6771793875693834006L;

    /**
     * 是否显示自己的状态信息
     */
    private Integer myself;

    /**
     * 根据状态信息查找
     */
    private Integer result;

    /**
     * 根据作者查询
     */
    private String username;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 竞赛id
     */
    private Long contest_id;

    /**
     * 问题id
     */
    private Long problem_id;

    // 下面为补充信息
    /**
     * 如果myself为真时，找出当前用户的id
     */
    private Long userId;

    /**
     * 如果username不为空时，根据模糊查询找到用户id列表
     */
    private List<Long> userList;
}
