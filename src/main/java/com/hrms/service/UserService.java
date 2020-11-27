package com.hrms.service;

import com.github.pagehelper.PageHelper;
import com.hrms.bean.Sentence;
import com.hrms.bean.User;
import com.hrms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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




    public int register(User user) {
        return userDao.register(user);
    }



    public User findUserById(Integer id){
        return userDao.findUserById(id);
    }


    public List<User> getUsers(Integer page, Integer size) {
        //pageNum是页码 pageSize是每页展示条数
        PageHelper.startPage(page,size);
        return userDao.getUsers();
    }

    public int banned(User user) {
        return userDao.banned(user);
    }

    public int modifyName(Integer id, String name) {
        return userDao.modifyName(id,name);
    }

    public int modifyPassWord(Integer id, String password) {
        return userDao.modifyPassWord(id,password);
    }

    public int modifyUsername(Integer id, String username) {
        return userDao.modifyUsername(id,username);
    }

    public int modifyHeadPhoto(Integer id, String headPhoto) {
        return userDao.modifyHeadPhoto(id,headPhoto);
    }
}
