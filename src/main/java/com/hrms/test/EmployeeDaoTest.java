package com.hrms.test;

import com.hrms.bean.Employee;
import com.hrms.dao.EmployeeDao;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.javassist.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @auther thk
 * @date 2020/10/22 - 10:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao dao;

    @Test
    public void deleteEmpById(){
        int i = dao.deleteEmpById(6);
        System.out.println(i);
    }

    @Test
    public void addEmp(){
        Employee employee = new Employee();
        employee.setEmpId(5);
        employee.setEmpName("嘘嘘");
        employee.setEmpEmail("aaa");
        employee.setGender("gg");
        employee.setDepartment(null);
        employee.setDepartmentId(1);
        int i = dao.addEmp(employee);
        System.out.println(i);
    }

    @Test
    public void getEmpById(){
        Employee employee = dao.selectById(1);
        System.out.println(employee);
    }


    @Test
    public void updateOneByIdTest(){
        Employee employee =
                new Employee(1, "aa", "aa@qq.com", "女", 3);
        int res = dao.updateEmpById(9, employee);
        System.out.println(res);
    }
}
