package com.spider.core.persist;

import com.spider.core.mapper.HouseInfoMapper;
import com.spider.core.mapper.HousePictureMapper;
import com.spider.core.mapper.HousePriceMapper;
import com.spider.core.model.HouseInfo;
import com.spider.core.model.HousePicture;
import com.spider.core.model.HousePrice;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class DouBanPipeLine implements Pipeline {
    @Autowired
    private HouseInfoMapper houseInfoMapper;

    @Autowired
    private HousePictureMapper housePictureMapper;

    @Autowired
    private HousePriceMapper housePriceMapper;

    @Override
    @Transactional
    public void process(ResultItems resultItems, Task task) {
        synchronized (this) {
            HouseInfo queryParam = new HouseInfo();
            queryParam.setUrl(resultItems.get("url"));
            HouseInfo existHouse = houseInfoMapper.selectOne(queryParam);
            if(existHouse != null){
                return ;
            }
            HouseInfo houseInfo = new HouseInfo();
            houseInfo.setTitle(resultItems.get("title"));
            houseInfo.setUrl(resultItems.get("url"));
            houseInfo.setContent(resultItems.get("content"));
            houseInfo.setCreateTime(new Date());
            try {
                houseInfo.setpublishTime(DateUtils.parseDate(resultItems.get("publishTime"),"yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<String> priceList = resultItems.get("price");
            List<HousePrice> housePriceList = new ArrayList<>();
            for(String price:priceList){
                HousePrice housePrice = new HousePrice();
                housePrice.setCreateTime(new Date());
                housePrice.setUrl(resultItems.get("url"));
                housePrice.setPrice(new BigDecimal(price));
                housePriceList.add(housePrice);
            }
            List<String> imgList = resultItems.get("imgs");
            List<HousePicture> housePictureList = new ArrayList<>();
            for(String picture:imgList){
                HousePicture housePicture = new HousePicture();
                housePicture.setCreateTime(new Date());
                housePicture.setPictureUrl(picture);
                housePicture.setUrl(resultItems.get("url"));
                housePictureList.add(housePicture);
            }
            houseInfoMapper.insertUseGeneratedKeys(houseInfo);
            if(CollectionUtils.isNotEmpty(housePriceList)) {
                housePriceMapper.insertList(housePriceList);
            }
            if(CollectionUtils.isNotEmpty(housePictureList)) {
                housePictureMapper.insertList(housePictureList);
            }
        }
    }
}
