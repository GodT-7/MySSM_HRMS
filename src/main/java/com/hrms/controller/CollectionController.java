package com.hrms.controller;

import com.github.pagehelper.PageInfo;
import com.hrms.bean.Review;
import com.hrms.bean.Sentence;
import com.hrms.bean.User;
import com.hrms.service.CollectionService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * @auther thk
 * @date 2020/11/22 - 21:57
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @ResponseBody
    @RequestMapping("/collect")
    public JsonMsg collect(@RequestParam("sentenceId") Integer sentenceId, HttpSession session){
        int res = 0;
        User user = (User)session.getAttribute("user");
        if(user == null){
            return JsonMsg.fail().addInfo("error","没有登陆");
        }
        res = collectionService.collected(sentenceId,user.getId());
        if(res == 1){
            res = collectionService.deletedCollect(sentenceId,user.getId());
            if (res != 1) {
                return JsonMsg.fail().addInfo("error", "取消异常");
            }
            return JsonMsg.success().addInfo("isok","isok_cancel");
        }else {
            res = collectionService.collect(sentenceId, user.getId());
            if (res != 1) {
                return JsonMsg.fail().addInfo("error", "收藏异常");
            }
            return JsonMsg.success().addInfo("isok","isok_collect");
        }
    }

    @ResponseBody
    @RequestMapping("/collected")
    public JsonMsg collected(@RequestParam("sentenceId") Integer sentenceId, HttpSession session){
        int res = 0;
        User user = (User)session.getAttribute("user");
        if(user == null)
            return JsonMsg.fail();
        res = collectionService.collected(sentenceId,user.getId());
        if(res == 1){
            return JsonMsg.success().addInfo("isok","isok_collect");
        }else {
            return JsonMsg.success().addInfo("isok","isok_noCollect");
        }
    }

    @ResponseBody
    @RequestMapping("/getCollection")
    public JsonMsg getCollection(@RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
                             @RequestParam(value = "size",required = true,defaultValue = "4")Integer size,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null)
            return JsonMsg.fail().addInfo("error","没有登陆");
        List<Sentence> sentences = collectionService.getCollection(page,size,user.getId());
        if(sentences == null)
            return JsonMsg.fail().addInfo("error","获取评论失败");
        PageInfo<Review> pageInfo = new PageInfo(sentences);
        return JsonMsg.success().addInfo("pageInfo",pageInfo);
    }


}
