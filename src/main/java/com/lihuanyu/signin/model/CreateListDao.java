package com.lihuanyu.signin.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by skyADMIN on 16/5/6.
 */
public interface CreateListDao extends CrudRepository<CreateList,Long> {
    public Iterable<CreateList> findByYibanid(int yibanid);
}
