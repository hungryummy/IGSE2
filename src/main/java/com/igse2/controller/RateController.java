package com.igse2.controller;


import com.github.pagehelper.Page;
import com.igse2.common.MessageConstant;
import com.igse2.common.PageResult;
import com.igse2.common.Result;
import com.igse2.common.StatusCode;
import com.igse2.entity.Rate;
import com.igse2.entity.Reading;
import com.igse2.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rate")
public class RateController {


    @Autowired
    private RateService rateService;


    @RequestMapping("/view")
    public Result view(){
        List <Rate> all = rateService.viewAll();
        return new Result(false,200,"successful",all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Rate> page = rateService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.COMMUNITY_SEARCH_SUCCESS, page.getResult(), page.getTotal() );
    }


    @RequestMapping("/findById")
    public Result findById(String taiffType){
        Rate rate = rateService.findById(taiffType);
        return new Result(true, StatusCode.OK, MessageConstant.COMMUNITY_FIND_BY_ID_SUCCESS,rate);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody Rate  rate){
        Boolean add = rateService.update1(rate);
        return new Result(true, StatusCode.OK, MessageConstant.COMMUNITY_UPDATE_SUCCESS);
    }



}



