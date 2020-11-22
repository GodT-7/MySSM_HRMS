package com.hrms.controller;

import com.hrms.bean.Sentence;
import com.hrms.bean.User;
import com.hrms.service.ISentenceServiceImpl;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @auther thk
 * @date 2020/11/22 - 10:13
 */
@Controller
@RequestMapping("/sentence")
public class SentenceController {

    @Autowired
    private ISentenceServiceImpl sentenceService;

    @ResponseBody
    @RequestMapping("/findNextSentence")
    public JsonMsg findNextSentence() {
        Sentence sentence = sentenceService.findNextSentence();
        if (sentence == null)
            return JsonMsg.fail().addInfo("error", "查找错误");
        return JsonMsg.success().addInfo("sentence", sentence.getSentence());
    }

    @ResponseBody
    @RequestMapping("/upload")
    public JsonMsg upload(@RequestParam("sentence") String sentence,
                          @RequestParam("author") String author, HttpSession session){

        User user = (User)session.getAttribute("user");
        int res = sentenceService.upload(user.getId(),sentence,author);
        if(res != 1){
            return JsonMsg.fail().addInfo("error","上传异常");
        }
        return JsonMsg.success().addInfo("isok","上传成功");
    }

}
