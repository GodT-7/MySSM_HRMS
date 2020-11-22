package com.hrms.service;

import com.hrms.bean.User;
import com.hrms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther thk
 * @date 2020/11/22 - 11:26
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }


    public int collect(Integer sentenceId, Integer userId) {
        return userDao.collect(sentenceId,userId);
    }

    public int register(User user) {
        return userDao.register(user);
    }



    public User findUserById(Integer id){
        return userDao.findUserById(id);
    }
}
