package cn.edu.nciae.onlinejudge.user.dto;

import cn.edu.nciae.onlinejudge.user.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/27 17:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    /**
     * 用户信息
     */
    UserInfo userInfo;

    /**
     * 角色类型
     */
    String roleType;

    /**
     * 问题权限
     */
    String problemPermission;

    /**
     * 已经解决的问题列表
     */
    List<Long> solvedProblemIdList;
}
