package cn.edu.nciae.onlinejudge.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/23 16:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO<T> {
    /**
     * Status
     */
    private String err;

    /**
     * Message Body
     */
    private T data;
}
