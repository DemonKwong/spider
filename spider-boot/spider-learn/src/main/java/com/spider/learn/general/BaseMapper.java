package com.spider.learn.general;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author kuangjiewen
 * @Title: BaseMapper
 * @ProjectName spider
 * @Description: TODO
 * @date 2018/11/27 16:08
 */

public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
