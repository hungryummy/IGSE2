package com.igse2.service.impl;

import com.github.pagehelper.Page;
import com.igse2.common.Result;
import com.igse2.entity.Customer;
import com.igse2.entity.Voucher;
import com.igse2.mapper.CustomerMapper;
import com.igse2.mapper.VoucherMapper;
import com.igse2.service.CustomerService;
import com.igse2.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private VoucherMapper voucherMapper;

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
        // 优惠券修改为已用
        voucher1.setUsed(1);
        //2.update used
        voucherMapper.updateByPrimaryKeyDiy(voucher1);
        // 余额加200
        String balance = customer1.getBalance();
        Integer balanceNow = Integer.parseInt(balance) + 200;
        customer1.setBalance(balanceNow.toString());
        customerMapper.updateByPrimaryKey(customer1);
        return true;
    }

}
