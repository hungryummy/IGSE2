package com.igse2.controller;


import com.github.pagehelper.Page;
import com.igse2.common.MessageConstant;
import com.igse2.common.PageResult;
import com.igse2.common.Result;
import com.igse2.common.StatusCode;
import com.igse2.entity.Customer;
import com.igse2.entity.Reading;
import com.igse2.entity.Recharge;
import com.igse2.service.CustomerService;
import com.igse2.service.RateService;
import com.igse2.service.ReadingService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReadingService readingService;
    

    @RequestMapping("/result")
    public Result find(){
        List<Customer> all = customerService.findAll();
        return new Result(false,200,"successful",all);
    }

    @RequestMapping("/view")
    public Result view(){
        List<Reading> all = readingService.viewAll();
        return new Result(false,200,"successful",all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Reading> page = readingService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.COMMUNITY_SEARCH_SUCCESS, page.getResult(), page.getTotal() );
    }
    @RequestMapping("/add")
    public Result add(@RequestBody Reading reading){
        Boolean add = readingService.add(reading);
        return new Result(true, StatusCode.OK, MessageConstant.COMMUNITY_ADD_SUCCESS );
    }

    @RequestMapping("/findById")
    public Result findById(Integer readingId){
      Reading reading = readingService.findById(readingId);
        return new Result(true, StatusCode.OK, MessageConstant.COMMUNITY_FIND_BY_ID_SUCCESS,reading);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Reading reading){
        Boolean add = readingService.update(reading);
        return new Result(true, StatusCode.OK, MessageConstant.COMMUNITY_UPDATE_SUCCESS);
    }
    @RequestMapping("/updateStatus/{status}/{readingId}/{customerId}")
    public Result updateStatus(@PathVariable("status") String status, @PathVariable("readingId") Integer readingId, @PathVariable("customerId") String customerId){
        Boolean flag = readingService.updateStatus(status,readingId,customerId);
        return new Result(true,StatusCode.OK, MessageConstant.COMMUNITY_UPDATE_STATUS_SUCCESS );
    }

    /**
     * 给用户充值
     * @return
     */
    @RequestMapping(value = "/reCharge",method = RequestMethod.POST)
    public Result reCharge(@RequestBody Recharge recharge){
        Boolean flag = customerService.reCharge(recharge.getVoucher(),recharge.getCustomerId());
        if (flag == true){
            return new Result(true,StatusCode.OK, MessageConstant.COMMUNITY_UPDATE_STATUS_SUCCESS );
        }
        return new Result(false,StatusCode.ERROR, "充值失败！" );
    }

}
