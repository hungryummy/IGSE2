package com.igse2.controller;

import com.igse2.auth.HashGenerator;
import com.igse2.common.Result;
import com.igse2.entity.Customer;
import com.igse2.mapper.CustomerMapper;
import com.igse2.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/user")
public class LoginController {  

    @Autowired
    private CustomerService customerService;


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

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public void loginOut(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute("email");
        session.invalidate();
    }
}