package com.spider.general;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author kuangjiewen
 * @Title: BaseMapper
 * @ProjectName spider
 * @Description: TODO
 * @date 2018/11/2716:08
 */
@Component
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
