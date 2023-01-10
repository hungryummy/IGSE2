package com.igse2.service.impl;

import com.github.pagehelper.Page;
import com.igse2.entity.Customer;
import com.igse2.mapper.CustomerMapper;
import com.igse2.service.CustomerService;
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

}
