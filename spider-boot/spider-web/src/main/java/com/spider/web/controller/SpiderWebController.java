package com.spider.web.controller;

import com.spider.web.service.HouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
public class SpiderWebController {
    @Autowired
    private HouseInfoService houseInfoService;

    @RequestMapping("/getList")
    public Page getList(@RequestParam int page, HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        return houseInfoService.getList(page,7);
    }
}
