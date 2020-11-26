package com.hrms.controller;

import com.hrms.bean.Sentence;
import com.hrms.bean.User;
import com.hrms.service.KeywordService;
import com.hrms.service.SentenceService;
import com.hrms.util.JsonMsg;
import com.hrms.util.SensitiveWordsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * @auther thk
 * @date 2020/11/22 - 10:13
 */
@Controller
@RequestMapping("/sentence")
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;


    @Autowired
    private KeywordService keywordService;

    @ResponseBody
    @RequestMapping("/findNextSentence")
    public JsonMsg findNextSentence() {
        Sentence sentence = sentenceService.findNextSentence();
        if (sentence == null)
            return JsonMsg.fail().addInfo("error", "查找错误");
        return JsonMsg.success().addInfo("sentence",sentence);
    }

    @ResponseBody
    @RequestMapping("/upload")
    public JsonMsg upload(@RequestParam("sentence") String sentence,
                        @RequestParam("author") String author, HttpSession session){
        ModelAndView mv = new ModelAndView();
        User user = (User)session.getAttribute("user");
        boolean flag = false;
        //敏感字过滤
        Set<String> keyWords = keywordService.selectAll();
        if (keyWords.size() != 0) {
            SensitiveWordsUtil.init(keyWords);
            boolean sentence_flag =SensitiveWordsUtil.contains(sentence);
            boolean author_flag = SensitiveWordsUtil.contains(author);
            if (sentence_flag||author_flag) {
                String replaceContent = SensitiveWordsUtil.replaceSensitiveWord(sentence, "**");
                Set<String> sensWords = SensitiveWordsUtil.getSensitiveWord(sentence);
                mv.addObject("result",replaceContent);
                mv.addObject("sensWords",sensWords);
            } else {
                mv.addObject("result", "allow");
                flag = true;
            }
        }

        if(user!=null) {
            if(sentence!=null&&sentence!="") {
                if(flag) {
                    sentenceService.upload(user.getId(), sentence, author);
                    return JsonMsg.success().addInfo("isok","成功啦");
                }else{
                    return JsonMsg.fail().addInfo("error","有敏感字");
                }

            }else{
                mv.addObject("error","没有输入");
                return JsonMsg.fail().addInfo("error","没有输入");
            }
        }else{
            mv.addObject("error","没有登陆");
            return JsonMsg.fail().addInfo("error","没有登陆");
        }
    }

}
