package com.spider.web.service.impl;

import com.spider.web.dao.HouseInfoDao;
import com.spider.web.entity.HouseInfo;
import com.spider.web.service.HouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service("houseInfoService")
public class HouseInfoServiceImpl implements HouseInfoService {

    @Autowired
    private HouseInfoDao houseInfoDao;

    @Override
    public Page<HouseInfo> getList(int page,int size) {
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"id");
        Page<HouseInfo> pageInfo = houseInfoDao.findAll(pageable);
        return pageInfo;
    }
}
