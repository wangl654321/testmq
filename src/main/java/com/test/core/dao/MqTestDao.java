package com.test.core.dao;

import com.test.core.entity.MqTest;
import org.springframework.stereotype.Repository;

@Repository
public interface MqTestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MqTest record);

    MqTest selectByPrimaryKey(Integer id);

    int update(MqTest record);
}