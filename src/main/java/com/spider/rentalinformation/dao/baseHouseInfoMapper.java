package com.spider.rentalinformation.dao;

import com.spider.rentalinformation.model.baseHouseInfo;

public interface baseHouseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(baseHouseInfo record);

    int insertSelective(baseHouseInfo record);

    baseHouseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(baseHouseInfo record);

    int updateByPrimaryKey(baseHouseInfo record);
}