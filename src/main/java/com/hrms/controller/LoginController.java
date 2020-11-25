package com.hrms.controller;

import com.hrms.bean.User;
import com.hrms.service.UserService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

/**
 * @auther thk
 * @date 2020/10/22 - 11:46
 */
@Controller
@RequestMapping("/wyy")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登陆操作，判断用户输入的账号密码是否正常，正常就跳转到main方法
     * 不正常就alert一下错误信息
     * @return
     */
    @ResponseBody //以json的方法
    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public JsonMsg doLogin(User user, HttpSession session){
        System.out.println(user.getUsername()+user.getPassword());
        User u = userService.findUserByUsername(user.getUsername());
        if(!u.getPassword().equals(user.getPassword())){
            return JsonMsg.fail().addInfo("error","输入账户密码错误");
        }
        if(u.getStatus() == 0)
            return JsonMsg.fail().addInfo("error","您的账户已被禁");
        session.setAttribute("u",u);
        return JsonMsg.success().addInfo("u",u);
    }

}
