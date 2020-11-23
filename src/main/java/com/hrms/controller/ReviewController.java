package com.hrms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrms.bean.Review;
import com.hrms.bean.User;
import com.hrms.service.ReviewService;
import com.hrms.service.UserService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @auther thk
 * @date 2020/11/22 - 18:43
 */
@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping("/review")
    public JsonMsg review(String review, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null)
            return JsonMsg.fail().addInfo("error","没有登陆");
        int res = 0;
        res = reviewService.review(user.getId(),review);
        if(res!=1){
            return JsonMsg.fail().addInfo("error","评论失败");
        }
        return JsonMsg.success().addInfo("isok","评论成功");
    }


    @ResponseBody
    @RequestMapping("/getReview")
    public JsonMsg getReview(@RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
                             @RequestParam(value = "size",required = true,defaultValue = "4")Integer size,HttpSession session){
        if(session.getAttribute("user") == null)
            return JsonMsg.fail().addInfo("error","没有登陆");
        List<Review> reviews = reviewService.getReview(page,size);
        if(reviews == null)
            return JsonMsg.fail().addInfo("error","获取评论失败");
        PageInfo<Review> pageInfo = new PageInfo(reviews);
        return JsonMsg.success().addInfo("pageInfo",pageInfo);
    }

}
