package com.yurlov.songLib.data.services.impl;

import com.yurlov.songLib.data.exception.ObjectNotFoundException;
import com.yurlov.songLib.data.services.IGenericService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class GenericService<T> implements IGenericService<T> {

    protected abstract JpaRepository<T, Long> getJpaRepository();

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public T getById(Long id) {
        return getJpaRepository().findById(id).orElseThrow(() -> new ObjectNotFoundException("No such object with id = " + id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public T create(T obj) {
        return getJpaRepository().save(obj);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Long id) {
        getJpaRepository().findById(id).ifPresentOrElse((x) -> getJpaRepository().deleteById(id),
                () -> new ObjectNotFoundException("No such object with id = " + id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<T> findAll() {
        return getJpaRepository().findAll();
    }
}
