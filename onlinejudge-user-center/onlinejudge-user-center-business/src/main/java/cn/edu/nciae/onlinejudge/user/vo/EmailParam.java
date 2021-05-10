package cn.edu.nciae.onlinejudge.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/7 23:20
 */
@Data
public class EmailParam implements Serializable {
    /**
     * 密码
     */
    private String password;

    /**
     * 旧邮箱
     */
    private String oldEmail;

    /**
     * 新邮箱
     */
    private String newEmail;
}
