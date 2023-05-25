package com.ttknpdev.h2.springbootcrudh2securetest.service;

import java.util.List;
import java.util.Map;

public interface UserService<T> {
    T create(T obj);
    List<T> creates(List<T> listObj);
    List<T> reads();
    T read(Long id);
    Map<String,T> update(T obj);
    Map<String,T> delete(Long id);

}
