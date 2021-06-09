package cn.edu.nciae.onlinejudge.oauth2.vo;

import lombok.Data;

/**
 * 用于检测username和email是否重复
 */
@Data
public class CheckParam {

    private String username;

    private String email;
}
