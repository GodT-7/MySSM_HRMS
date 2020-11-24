package com.hrms.controller;

import com.github.pagehelper.PageInfo;
import com.hrms.bean.Review;
import com.hrms.bean.User;
import com.hrms.service.UserService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @auther thk
 * @date 2020/11/22 - 11:47
 */
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



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
        User user = (User)session.getAttribute("user");
        if(user != null)
            session.removeAttribute("user");
        System.out.println("hello");
        return JsonMsg.success();
    }


    @ResponseBody
    @RequestMapping("/getUsers")
    public JsonMsg getUsers(@RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
                            @RequestParam(value = "size",required = true,defaultValue = "4")Integer size,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null)
            return JsonMsg.fail().addInfo("error","没有登陆");
        if(!user.getPermissions().equals("admin"))
            return JsonMsg.fail().addInfo("error","权限不够");
        List<User> users = userService.getUsers(page,size);
        if(user == null)
            return JsonMsg.fail().addInfo("error","获取用户失败");
        PageInfo<User> pageInfo = new PageInfo(users);
        return JsonMsg.success().addInfo("pageInfo",pageInfo);
    }

    @ResponseBody
    @RequestMapping("/banned")
    public JsonMsg banned(Integer id){
        User user = userService.findUserById(id);
        if(user == null)
            return JsonMsg.fail().addInfo("error","查无此人");
        if(user.getStatus() == 0)
            return JsonMsg.fail().addInfo("error","该用户已被封禁");
        user.setStatus(0);
        int res = userService.banned(user);
        if(res!=1){
            return JsonMsg.fail().addInfo("error","封禁失败");
        }
        return JsonMsg.success().addInfo("isok","封禁成功");
    }

}
