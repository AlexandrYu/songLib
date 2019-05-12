package com.yurlov.songLib.data.services;

import java.util.List;

public interface IGenericService<T> {

    T getById(Long id);

    T create(T obj);

    void delete(Long id);

    List<T> findAll();

}
