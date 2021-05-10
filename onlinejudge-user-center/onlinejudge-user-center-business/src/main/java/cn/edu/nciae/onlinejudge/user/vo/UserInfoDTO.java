package cn.edu.nciae.onlinejudge.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/18 21:40
 */

/**
 * 获取用户信息的DTO
 */
@Data
public class UserInfoDTO implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户真实姓名
     */
    private String userRealName;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 注册时间
     */
    private LocalDateTime userRegtime;

    /**
     * 解题数量
     */
    private Integer userSolveNumber;

    /**
     * 界面语言
     */
    private String userLanguage;

    /**
     * 学校
     */
    private String userSchool;

    /**
     * 座右铭
     */
    private String userMotto;

    /**
     * 博客
     */
    private String userBlog;

    /**
     * Base64头像
     */
    private String userAvatar;

    /**
     * OSS头像URL
     */
    private String userAvatarUrl;

    /**
     * Github Url
     */
    private String userGithub;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

}
