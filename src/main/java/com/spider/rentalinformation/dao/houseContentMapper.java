package com.spider.rentalinformation.dao;

import com.spider.rentalinformation.model.houseContent;

public interface houseContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(houseContent record);

    int insertSelective(houseContent record);

    houseContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(houseContent record);

    int updateByPrimaryKeyWithBLOBs(houseContent record);

    int updateByPrimaryKey(houseContent record);
}