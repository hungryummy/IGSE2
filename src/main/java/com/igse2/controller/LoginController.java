package com.igse2.controller;

import com.igse2.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/user")
public class LoginController {  

//    @Autowired
//    private UserMapper userMapper;

    @CrossOrigin
    @PostMapping(value = "/login")
    public Result login(@RequestParam("username") String userName,
    					@RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response) {

        //session方式
        HttpSession session = request.getSession();
        session.setAttribute("username", userName);    //session中存的值
//        User user = userMapper.getUserByName(userName);
//        if (user == null) {
//            System.out.println("用户名为空！！");
//        }
//        else if (password.equals(user.getPassword())) {
//            return new Result(ResponseCode.successCode, "登录成功");
//        } else {
//            return new Result(ResponseCode.passwordErrorCode, "密码错误");
//        }
//        return new Result(ResponseCode.passwordErrorCode, "用户名或密码错误");
        session.setMaxInactiveInterval(1800);  // 设置session失效时间为30分钟
        return new Result(true,200,"登录成功");
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public void loginOut(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute("username");
        session.invalidate();
    }
}