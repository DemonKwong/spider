package com.spider.core.dao;

import com.spider.core.model.HouseContent;

public interface HouseContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseContent record);

    int insertSelective(HouseContent record);

    HouseContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseContent record);

    int updateByPrimaryKeyWithBLOBs(HouseContent record);

    int updateByPrimaryKey(HouseContent record);
}