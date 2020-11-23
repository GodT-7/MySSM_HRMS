package com.hrms.service;

import com.hrms.bean.Sentence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @auther thk
 * @date 2020/11/22 - 17:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class testService {

    @Autowired
    private SentenceService sentenceService;

    @Test
    public void test(){
        Sentence nextSentence = sentenceService.findNextSentence();
        System.out.println(nextSentence);
    }
}
