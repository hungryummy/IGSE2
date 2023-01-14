package com.igse2.controller;

import com.igse2.auth.HashGenerator;
import com.igse2.common.Result;
import com.igse2.entity.Customer;
import com.igse2.entity.Voucher;
import com.igse2.mapper.VoucherMapper;
import com.igse2.service.CustomerService;
import com.igse2.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private VoucherService voucherService;

    @Autowired
    private VoucherMapper voucherMapper;


    @CrossOrigin
    @PostMapping(value = "/login")
    public Result login(@RequestParam("email") String email,
    					@RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response) {

        //session方式
        HttpSession session = request.getSession();
        Customer user = customerService.getUserByEmail(email);
        // 密码加密
        String sha256Password = HashGenerator.getSHA256(password);
        if (user == null) {
            System.out.println("用户名为空！！");
        }
        else if (sha256Password.equals(user.getPasswordHash())) {
            session.setAttribute("email", email);    //session中存的值
            session.setMaxInactiveInterval(1800);  // 设置session失效时间为30分钟
            return new Result(true,200,"登录成功");
        } else {
            return new Result(false,304,"密码错误");
        }
        return new Result(false,304,"用户名或密码错误");

    }

    @CrossOrigin
    @PostMapping(value = "/signUp")
    public Result signUp(@RequestBody Customer customer,@RequestParam("voucher") String voucher,
                        HttpServletRequest request, HttpServletResponse response) {
        if (customer.getCustomerId() == null || "".equals(customer.getCustomerId())){
            return new Result(false,304,"用户名不规范");
        }
        Customer user = customerService.getUserByEmail(customer.getCustomerId());
        if (user != null) {
            return new Result(false,304,"用户名已存在");
        }
        //voucher字段是优惠券
        //1.check voucher, valid if it exists and used = 0
        //customer.setBalance("200");
        // 2. "used" +1
//        List<Voucher> vouchers = voucherService.findAll();
//        for (int i = 0; i < vouchers.size(); i++) {
//            if( vouchers.get(i).getEVCCode() == voucher ){
//                if (vouchers.get(i).getUsed()== 1){
//                    return new Result(false,304,"the code has been used");
//                }else if (vouchers.get(i).getUsed()== 0){
//                    customer.setBalance("200");
//                    vouchers.get(i).setUsed(1);
//                }
//            }
//        }
        Voucher voucher1 = voucherService.queryUsedExist(voucher);
        if(voucher1 == null){
            //y优惠券不存在
            return new Result(false,304,"code does not exist");
        } else if (voucher1.getUsed() == 1) {
            //y已被使用
            return new Result(false,304,"the code has been used");
        }
        //1.customer.setBalance("200");

        customer.setBalance("200");
        voucher1.setUsed(1);
        //2.update 优惠券，置为y已使用
        voucherMapper.updateByPrimaryKey(voucher1);
        // hashcode
        customer.setPasswordHash(HashGenerator.getSHA256(customer.getPasswordHash()));
        customerService.insert(customer);
        return new Result(true,200,"注册成功",customer);
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public void loginOut(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute("email");
        session.invalidate();
    }
}