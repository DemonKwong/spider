package com.spider.rentalinformation.dao;

import com.spider.rentalinformation.model.BaseHouseInfo;

public interface BaseHouseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseHouseInfo record);

    int insertSelective(BaseHouseInfo record);

    BaseHouseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseHouseInfo record);

    int updateByPrimaryKey(BaseHouseInfo record);
}