package com.spider.dao.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.spider.dao.api.HousePriceMapper;
import com.spider.dao.dto.HouseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Service(interfaceClass = HousePriceMapper.class,timeout = 600000)
@Component("housePriceMapper")
public interface HousePriceMapperImpl extends HousePriceMapper<HouseInfo> {

}
