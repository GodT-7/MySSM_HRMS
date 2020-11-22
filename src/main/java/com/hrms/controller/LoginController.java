package com.hrms.controller;

import com.hrms.bean.User;
import com.hrms.service.UserService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.SunHints;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
     * 登陆方法
     * 跳转到登陆页面去
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

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
        session.setAttribute("user",u);
        return JsonMsg.success().addInfo("isok","登陆成功");
    }

    /**
     * 跳转入主界面
     * @return
     */
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    /**
     * 跳转入登陆页面
     * @return
     */
    @RequestMapping(value = "/loginOut",method = RequestMethod.GET)
    public String loginOut(){
        return "login";
    }


}
