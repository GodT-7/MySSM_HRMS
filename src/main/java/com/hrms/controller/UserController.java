package com.hrms.controller;

import com.github.pagehelper.PageInfo;
import com.hrms.bean.User;
import com.hrms.service.UserService;
import com.hrms.util.JsonMsg;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * @auther thk
 * @date 2020/11/22 - 11:47
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/isLoad")
    public JsonMsg isLoad(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user != null)
            return JsonMsg.success().addInfo("u",user);
        return JsonMsg.fail().addInfo("error","没有登陆");
    }

    @ResponseBody
    @RequestMapping("/register")
    public JsonMsg Register(User user,HttpSession session){
        user.setStatus(3);
        user.setHeadPhotoSrc("auto.png");
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

    @ResponseBody
    @RequestMapping("/modifyName")
    public JsonMsg modifyName(@RequestParam("name") String name,HttpSession session){
        User user = (User)session.getAttribute("user");
        int res = userService.modifyName(user.getId(),name);
        if(res!=1)
            return JsonMsg.fail().addInfo("error","修改失败");
        return JsonMsg.success().addInfo("isok","修改成功");
    }

    @ResponseBody
    @RequestMapping("/modifyPassWord")
    public JsonMsg modifyPassWord(@RequestParam("password") String password,HttpSession session){
        User user = (User)session.getAttribute("user");
        int res = userService.modifyPassWord(user.getId(),password);
        if(res!=1)
            return JsonMsg.fail().addInfo("error","修改失败");
        return JsonMsg.success().addInfo("isok","修改成功");
    }

    @ResponseBody
    @RequestMapping("/modifyUsername")
    public JsonMsg modifyUsername(@RequestParam("username") String username,HttpSession session){
        User user = (User)session.getAttribute("user");
        int res = userService.modifyUsername(user.getId(),username);
        if(res!=1)
            return JsonMsg.fail().addInfo("error","修改失败");
        return JsonMsg.success().addInfo("isok","修改成功");
    }

    @ResponseBody
    @RequestMapping("/modifyHeadPhotoSrc")
    public JsonMsg modifyHeadPhoto(@RequestParam("headPhoto") String headPhoto,HttpSession session){
        User user = (User)session.getAttribute("user");
        int res = userService.modifyHeadPhoto(user.getId(),headPhoto);
        if(res!=1)
            return JsonMsg.fail().addInfo("error","修改失败");
        return JsonMsg.success().addInfo("isok","修改成功");
    }

    @ResponseBody
    @RequestMapping("/getUser")
    public JsonMsg getUser(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null)
            return JsonMsg.fail().addInfo("error","没有登陆");
        return JsonMsg.success().addInfo("u",user);
    }

    @ResponseBody
    @RequestMapping("/modifyHeadPhoto")
    public JsonMsg headFileUpload(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);

        User user = (User)session.getAttribute("user");

        request.setCharacterEncoding("utf-8");

        //设置缓冲区大小与临时文件目录
        factory.setSizeThreshold(1024*1024*10);
        File uploadTemp=new File("d:\\uploadTemp");
        uploadTemp.mkdirs();
        factory.setRepository(uploadTemp);

        //设置单个文件大小限制
        upload.setFileSizeMax(1024*1024*10);
        //设置所有文件总和大小限制
        upload.setSizeMax(1024*1024*30);
        String uuid= null;
        String suffix= null;
        try {
            List<FileItem> list=upload.parseRequest(request);
            System.out.println(list);
            for (FileItem fileItem:list){
                if (!fileItem.isFormField()&&fileItem.getName()!=null&&!"".equals(fileItem.getName())){
                    String filName=fileItem.getName();
                    //利用UUID生成伪随机字符串，作为文件名避免重复
                    uuid= user.getId().toString();
                    //获取文件后缀名
                    suffix=filName.substring(filName.lastIndexOf("."));

                    //获取文件上传目录路径，在项目部署路径下的upload目录里。若想让浏览器不能直接访问到图片，可以放在WEB-INF下
                    String uploadPath=request.getSession().getServletContext().getRealPath("/PIC");

                    File file=new File(uploadPath);
                    file.mkdirs();
                    //写入文件到磁盘，该行执行完毕后，若有该临时文件，将会自动删除
                    fileItem.write(new File(uploadPath,uuid+suffix));

                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.fail().addInfo("error","上传失败");
        }
        return JsonMsg.success().addInfo("headPhotoSrc",uuid+suffix);
    }
}
