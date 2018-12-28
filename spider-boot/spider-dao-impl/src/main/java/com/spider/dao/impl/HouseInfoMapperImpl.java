package com.spider.dao.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.spider.dao.api.HouseInfoMapper;
import com.spider.dao.dto.HouseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Service(interfaceClass = HouseInfoMapper.class,timeout = 600000)
@Component("houseInfoMapper")
public interface HouseInfoMapperImpl extends HouseInfoMapper<HouseInfo> {
	@Override
	Integer countNewInfoInYesterday();


}
