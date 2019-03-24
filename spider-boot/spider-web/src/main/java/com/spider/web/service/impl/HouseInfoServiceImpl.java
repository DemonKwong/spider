package com.spider.web.service.impl;

import com.spider.web.dao.HouseInfoDao;
import com.spider.web.entity.HouseInfo;
import com.spider.web.service.HouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@Service("houseInfoService")
public class HouseInfoServiceImpl implements HouseInfoService {

    @Autowired
    private HouseInfoDao houseInfoDao;

    @Override
    public Page<HouseInfo> getList(int page,int size) {
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"publishTime");
        Page<HouseInfo> pageInfo = houseInfoDao.findAll(pageable);
        Specification<HouseInfo> specification = new Specification<HouseInfo>() {
            @Override
            public Predicate toPredicate(Root<HouseInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        houseInfoDao.findAll(specification,pageable);
        Iterator<HouseInfo> infoIterator = pageInfo.iterator();
        while(infoIterator.hasNext()){
            HouseInfo houseInfo = infoIterator.next();
            String content = houseInfo.getContent();
            content = content.substring(1,content.length()-1);
            List<String> list =Arrays.asList(content.split(","));
            if(!CollectionUtils.isEmpty(list)){
                content = list.get(0);
            }else{
                content = "";
            }
            houseInfo.setContent(content);
        }
        return pageInfo;
    }
}
