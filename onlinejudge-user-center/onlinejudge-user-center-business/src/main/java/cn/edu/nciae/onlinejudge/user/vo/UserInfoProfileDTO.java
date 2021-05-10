package cn.edu.nciae.onlinejudge.user.vo;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/18 22:02
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 用于修改用户profile的DTO
 */
@Data
public class UserInfoProfileDTO implements Serializable {

    /**
     * 用户真实姓名
     */
    private String userRealName;

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
    private String userMood;

    /**
     * 博客
     */
    private String userBlog;

    /**
     * Github Url
     */
    private String userGithub;

}
