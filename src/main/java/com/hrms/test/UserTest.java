package com.hrms.test;

import com.hrms.bean.User;
import com.hrms.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @auther thk
 * @date 2020/11/22 - 14:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findUserByUsername(){
        User thk = userDao.findUserByUsername("thk");
        System.out.println(thk);
    }

    @Test
    public void register(){
        User user = new User();
        user.setUsername("ksrLoveThk");
        user.setPassword("ksrLoveThk");
        int res = userDao.register(user);
        System.out.println(res);
    }

}
