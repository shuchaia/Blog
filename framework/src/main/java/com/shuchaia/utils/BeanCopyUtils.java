package com.shuchaia.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName BeanCopyUtils
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/27 16:17
 * @Version 1.0
 */
public class BeanCopyUtils {
    private BeanCopyUtils(){}

    public static <V> V copyBean(Object source, Class<V> clazz){
        V target = null;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return target;
    }

    public static <V> List<V> copyBeanList(List<?> sourceList, Class<V> clazz){
        return sourceList.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
