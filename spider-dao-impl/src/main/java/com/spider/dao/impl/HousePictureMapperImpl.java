package com.spider.dao.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.spider.dao.api.HousePictureMapper;
import com.spider.dao.dto.HousePicture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Service(interfaceClass = HousePictureMapper.class,timeout = 600000)
@Component("housePictureMapper")
public interface HousePictureMapperImpl extends HousePictureMapper<HousePicture> {
}
