package com.spider.rentalinformation.dao;

import com.spider.rentalinformation.model.HouseContent;

public interface HouseContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseContent record);

    int insertSelective(HouseContent record);

    HouseContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseContent record);

    int updateByPrimaryKeyWithBLOBs(HouseContent record);

    int updateByPrimaryKey(HouseContent record);
}