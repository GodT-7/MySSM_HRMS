package com.hrms.service;

import com.github.pagehelper.PageHelper;
import com.hrms.bean.Collection;
import com.hrms.bean.Sentence;
import com.hrms.dao.CollectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther thk
 * @date 2020/11/22 - 22:02
 */
@Service
@Transactional
public class CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    public int collect(Integer sentenceId, Integer userId) {
        return collectionDao.collect(sentenceId,userId);
    }

    public List<Sentence> getCollection(Integer page, Integer size,Integer userId) {
        //pageNum是页码 pageSize是每页展示条数
        PageHelper.startPage(page,size);
        return collectionDao.getCollection(userId);
    }

    public int collected(Integer sentenceId, Integer userId) {
        Collection collection = collectionDao.collected(sentenceId,userId);
        if(collection == null)
            return 0;
        else
            return 1;
    }

    public int deletedCollect(Integer sentenceId, Integer userId) {
        return collectionDao.deletedCollect(sentenceId,userId);
    }
}
