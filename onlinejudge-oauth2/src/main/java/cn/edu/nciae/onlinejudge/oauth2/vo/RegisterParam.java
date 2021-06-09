package cn.edu.nciae.onlinejudge.oauth2.vo;

import lombok.Data;

/**
 * 注册时的参数
 */
@Data
public class RegisterParam {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;
}
