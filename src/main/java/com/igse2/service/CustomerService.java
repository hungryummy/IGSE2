package com.igse2.service;

import com.github.pagehelper.Page;
import com.igse2.entity.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    public List<Customer> findAll();


    Customer getUserByEmail(String email);

    void insert(Customer customer);

    Boolean reCharge(String voucher, String userEmail);
}
