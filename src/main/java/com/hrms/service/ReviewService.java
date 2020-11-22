package com.hrms.service;

import com.github.pagehelper.PageHelper;
import com.hrms.bean.Review;
import com.hrms.bean.User;
import com.hrms.dao.ReviewDao;
import com.hrms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther thk
 * @date 2020/11/22 - 11:26
 */
@Service
public class ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    public int review(Integer userId,String review) {
        return reviewDao.review(userId,review);
    }

    public List<Review> getReview(int page,int size) {
        //pageNum是页码 pageSize是每页展示条数
        PageHelper.startPage(page,size);
        return reviewDao.getReview();
    }

}
