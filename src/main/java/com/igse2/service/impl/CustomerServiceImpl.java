package com.igse2.service.impl;

import com.github.pagehelper.Page;
import com.igse2.common.Result;
import com.igse2.entity.Customer;
import com.igse2.entity.Rate;
import com.igse2.entity.Reading;
import com.igse2.entity.Voucher;
import com.igse2.mapper.CustomerMapper;
import com.igse2.mapper.RateMapper;
import com.igse2.mapper.VoucherMapper;
import com.igse2.service.CustomerService;
import com.igse2.service.RateService;
import com.igse2.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private VoucherMapper voucherMapper;

    @Autowired
    private RateMapper rateMapper;

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerMapper.selectAll();
        return customers;
    }

    @Override
    public Customer getUserByEmail(String email) {
        Customer customer = new Customer();
        customer.setCustomerId(email);
        customer = customerMapper.selectOne(customer);
        return customer;
    }

    @Override
    public void insert(Customer customer) {
        customerMapper.insert(customer);
    }

    @Override
    public Boolean reCharge(String voucher, String userEmail) {
        // 先去查用户是否存在
        Customer customer = new Customer();
        customer.setCustomerId(userEmail);
        Customer customer1 = customerMapper.selectOne(customer);
        if (customer1 == null){
            return false;
        }
        // 再去查优惠券是否使用过
        Voucher voucher1 = voucherService.queryUsedExist(voucher);
        if(voucher1 == null){
            //not  valid
            return false;
        } else if (voucher1.getUsed() == 1) {
            //has been used
            return false;
        }
        // been used
        voucher1.setUsed(1);
        //2.update used
        voucherMapper.updateByPrimaryKeyDiy(voucher1);
        // 余额加200
        String balance = customer1.getBalance();
        float v = Float.parseFloat(balance) + 200f;
        customer1.setBalance(String.valueOf(v));
        customerMapper.updateByPrimaryKey(customer1);
        return true;
    }

    @Override
    public Result payBill(Reading reading) {
        //query rate
        List<Rate> rates = rateMapper.selectAll();
        //calculate the balance
        Map<String, String> collect = rates.stream().collect(Collectors.toMap(Rate::getTaiffType, e -> e.getRate()));
        //calculate
        Integer elecReadingsDay = reading.getElecReadingsDay();
        Integer gasReading = reading.getGasReading();
        Integer eletReadingNight = reading.getEletReadingNight();
        float v = Float.parseFloat(collect.get("electricity_day")) * elecReadingsDay
                + Float.parseFloat(collect.get("electricity_night")) * eletReadingNight
                + Float.parseFloat(collect.get("gas")) * gasReading;

        // yue
        Customer customer = new Customer();
        customer.setCustomerId(reading.getCustomerId());
        Customer customer1 = customerMapper.selectOne(customer);
        String balance = customer1.getBalance();
        float v1 = Float.parseFloat(balance);
        if (v1 < v){
            //not enough
            return new Result(false,400,"not enough money");
        }
        float v2 = v1 - v;
        String s = String.valueOf(v2);
        customer1.setBalance(s);
        int i = customerMapper.updateByPrimaryKey(customer1);
        // suc
        return new Result(true,200,"pay successfully");
    }

}
