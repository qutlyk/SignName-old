package com.lihuanyu.signin.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by skyADMIN on 16/3/4.
 */
public interface SignListDao extends CrudRepository<SignList,Long> {
    public Collection<SignList> findByYibanidAndCreateid(int yibanid, int createid);
    public Collection<SignList> findByCreateid(int createid);
}
