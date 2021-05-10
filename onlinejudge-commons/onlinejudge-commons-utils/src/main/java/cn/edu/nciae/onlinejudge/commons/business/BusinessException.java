package cn.edu.nciae.onlinejudge.commons.business;

/**
 * 全局业务异常
 * <p>
 * Description:
 * </p>
 *
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/15 20:41
 * @see cn.edu.nciae.onlinejudge.commons.business
 */
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = 1250615706628843606L;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException() {

    }

    public BusinessException(BusinessStatus status) {
        super(status.getMessage());
        this.code = status.getCode();
    }
}
