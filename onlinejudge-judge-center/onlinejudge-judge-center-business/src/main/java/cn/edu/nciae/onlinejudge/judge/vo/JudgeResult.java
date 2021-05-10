package cn.edu.nciae.onlinejudge.judge.vo;

import cn.edu.nciae.onlinejudge.commons.business.BusinessStatus;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/6 9:55
 */
public enum JudgeResult {

    /**
     * 编译错误
     */
    COMPILE_ERROR(-2,"Compile Error"),

    /**
     * 答案错误
     */
    WRONG_ANSWER(-1,"Wrong Answer"),

    /**
     * 答案通过
     */
    SUCCESS(0,"Accepted"),

    /**
     * CPU时间超时
     */
    CPU_TIME_LIMIT_EXCEEDED(1,"CPU Time Limit Exceeded"),

    /**
     * 真实时间超时
     */
    REAL_TIME_LIMIT_EXCEEDED(2,"Real Time Limit Exceeded"),

    /**
     * 内存超出
     */
    MEMORY_LIMIT_EXCEEDED(3,"Memory Limit Exceeded"),

    /**
     * 运行时错误
     */
    RUNTIME_ERROR(4,"Runtime Error"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(5,"System Error"),

    /**
     * 评测排队中
     */
    PENDING(6,"Pending"),

    /**
     * 正在评测
     */
    JUDGING(7,"Judging"),

    /**
     * 部分正确
     */
    PARTIAL_ACCEPTED(8,"Partial Accepted"),

    /**
     * 正在提交
     */
    SUBMITTING(9,"Submitting");

    private Integer code;
    private String message;

    JudgeResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessage(int code) {
        for (JudgeResult result : values()) {
            if (result.getCode().equals(code)) {
                return result.getMessage();
            }
        }
        return null;
    }

}
