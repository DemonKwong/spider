package com.spider.web.service;

import com.spider.web.entity.HouseInfo;
import org.springframework.data.domain.Page;


public interface HouseInfoService {
    Page<HouseInfo> getList(int page,int size);
}
