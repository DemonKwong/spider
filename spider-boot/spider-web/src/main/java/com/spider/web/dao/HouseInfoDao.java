package com.spider.web.dao;

import com.spider.web.entity.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HouseInfoDao extends JpaRepository<HouseInfo,Long>, JpaSpecificationExecutor<HouseInfo> {
}
