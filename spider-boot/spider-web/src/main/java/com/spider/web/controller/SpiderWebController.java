package com.spider.web.controller;

import com.spider.web.service.HouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
public class SpiderWebController {
    @Autowired
    private HouseInfoService houseInfoService;

    @RequestMapping("/getList")
    @ResponseBody
    public Page getList(@RequestParam int page, HttpServletResponse httpServletResponse){
        return houseInfoService.getList(page,7);
    }

    @RequestMapping("/list")
    public String listPage(Model model){
        model.addAttribute("ctx","http://localhost:8888/web");
        return "lightlog-index";
    }
}
