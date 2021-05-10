package cn.edu.nciae.onlinejudge.oauth2.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/15 20:37
 * @see cn.edu.nciae.onlinejudge.oauth2.vo
 */
@Data
public class LoginParam implements Serializable {

    private String username;
    private String password;

}
