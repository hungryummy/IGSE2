package com.igse2.controller;


import com.igse2.common.Result;
import com.igse2.entity.Customer;
import com.igse2.entity.Igse;
import com.igse2.entity.Reading;
import com.igse2.service.CustomerService;
import com.igse2.service.IgseService;
import com.igse2.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("igse")
public class IGSEController {
    @Autowired
    private IgseService igseServicee;


    @RequestMapping("/propertycount")
    public Object property(){
        List<Igse> all = igseServicee.findAll();
        Map<String, Long > groupListMap = all.stream().collect(Collectors.groupingBy(Igse::getPropertyType, Collectors.counting()));
        Object o= groupListMap;
        return o;

    }

    @RequestMapping(value ="/{propertyType}/{roomNum}")
    public Result find(@PathVariable("propertyType") String propertyType,@PathVariable("roomNum") String roomNum){

        // 计算平均每日开销
        Double money = igseServicee.costPerDay(propertyType,roomNum);
//        Map<String, Long > groupListMap = all.stream().collect(Collectors.groupingBy(Igse::getPropertyType, Collectors.counting()));
        Map<String, Object> res = new HashMap<>();
        res.put("propertyType",propertyType);
        res.put("roomNum",roomNum);
        res.put("per_day",money);
        res.put("unit","pound");
        return new Result(true,200,"successful",res);
    }

}
