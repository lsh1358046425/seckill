package com.lucien.seckill.entity;

import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/25 20:56
 */

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeneralConvertor {

    final private Mapper mapper;

    /**
     * List 实体类 转换器
     * @param source 原数据
     * @param clazz 转换类型
     * @return List
     */
    public <T, S> List<T> convertor(List<S> source, Class<T> clazz) {
        if (source == null) return null;
        List<T> map = new ArrayList<>();
        for (S s : source) {
            map.add(mapper.map(s, clazz));
        }
        return map;
    }

    /**
     * Set 实体类 深度转换器
     * @param source 原数据
     * @param clazz 目标对象
     * @return Set
     */
    public <T, S> Set<T> convertor(Set<S> source, Class<T> clazz) {
        if (source == null) return null;
        Set<T> set = new TreeSet<>();
        for (S s : source) {
            set.add(mapper.map(s, clazz));
        }
        return set;
    }

    /**
     * 类转换器
     * @param source 原数据
     * @param clazz 转换类型
     * @return T
     */
    public <T, S> T convertor(S source, Class<T> clazz) {
        if (source == null) return null;
        return mapper.map(source, clazz);
    }

}