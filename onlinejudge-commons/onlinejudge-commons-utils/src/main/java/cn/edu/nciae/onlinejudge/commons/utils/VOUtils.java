package cn.edu.nciae.onlinejudge.commons.utils;

import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/4/20 11:17
 */
public class VOUtils {
    public static <S, T> T getSuperObjectFromSubObject(@NotNull S src, Class<T> superClassName) {
        try {
            T target = superClassName.getConstructor().newInstance();
            BeanUtils.copyProperties(src, target);
            return target;
        } catch (Exception e) {
            throw new ClassCastException("Convert subclass object to the superclass object failed.\n" + e.getMessage());
        }
    }
}
