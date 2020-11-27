package com.hrms.service;

import com.hrms.bean.Sentence;
import com.hrms.dao.SentenceDao;
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
public class SentenceService {

    @Autowired
    private SentenceDao sentenceDao;

    public Sentence findNextSentence() {
        int count = sentenceDao.selectCount();
        Random rand = new Random();
        int id = rand.nextInt(count)+1;
        return sentenceDao.findNextSentence(id);
    }

    public int upload(Integer userId, String sentence, String author) {
        int count = sentenceDao.selectCount();
        return sentenceDao.upload(count+1,userId,sentence,author);
    }
}
