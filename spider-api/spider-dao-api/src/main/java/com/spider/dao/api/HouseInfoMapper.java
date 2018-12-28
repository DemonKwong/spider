package com.spider.dao.api;

public interface HouseInfoMapper<T> extends BaseMapper {

	/**
	 * 查询上一天新发布的租房信息数量
	 * */
	Integer countNewInfoInYesterday();
}