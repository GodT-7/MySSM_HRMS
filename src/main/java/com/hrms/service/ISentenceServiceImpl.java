package com.hrms.service;

import com.hrms.bean.Sentence;
import com.hrms.dao.ISentenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @auther thk
 * @date 2020/11/22 - 10:15
 */
@Service
@Transactional
public class ISentenceServiceImpl{

    @Autowired
    private ISentenceDao sentenceDao;

    public Sentence findNextSentence() {
        int count = sentenceDao.selectCount();
        Random rand = new Random();
        int id = rand.nextInt(3)+1;
        return sentenceDao.findNextSentence(id);
    }

    public int upload(Integer userId, String sentence, String author) {
        return sentenceDao.upload(userId,sentence,author);
    }
}
