package com.igse2.controller;


import com.igse2.common.Result;
import com.igse2.entity.Customer;
import com.igse2.entity.Igse;
import com.igse2.service.CustomerService;
import com.igse2.service.IgseService;
import com.igse2.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(value ="/{propertyType}")
    public Result find(){
        List<Igse> all = igseServicee.findAll();
        Map<String, Long > groupListMap = all.stream().collect(Collectors.groupingBy(Igse::getPropertyType, Collectors.counting()));
        return new Result(true,200,"successful",groupListMap);
    }

}
