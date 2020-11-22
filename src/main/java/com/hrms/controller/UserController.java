package com.hrms.controller;

import com.hrms.bean.User;
import com.hrms.service.UserService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @auther thk
 * @date 2020/11/22 - 11:47
 */
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/collect")
    public JsonMsg collect(@RequestParam("id") Integer id, HttpSession session){
        int res = 0;
        User user = (User)session.getAttribute("user");
        if(user == null){
            return JsonMsg.fail().addInfo("error","没有登陆");
        }
        res = userService.collect(id,user.getId());
        if(res != 1){
            return JsonMsg.fail().addInfo("error","收藏异常");
        }

        return JsonMsg.success().addInfo("isok","收藏成功");
    }

    @ResponseBody
    @RequestMapping("/register")
    public JsonMsg Register(User user,HttpSession session){
        user.setStatus(1);
        user.setPermissions("ordinary");
        int res = userService.register(user);
        if(res != 1){
            return JsonMsg.fail().addInfo("error","注册失败");
        }
        session.setAttribute("user",user);
        return JsonMsg.success().addInfo("isok","注册成功");
    }

    @ResponseBody
    @RequestMapping("/loginOut")
    public JsonMsg loginOut(HttpSession session){
        session.removeAttribute("user");
        return JsonMsg.success();
    }

}
