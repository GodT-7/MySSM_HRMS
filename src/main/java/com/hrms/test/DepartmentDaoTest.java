package com.hrms.test;

import com.hrms.bean.Department;
import com.hrms.dao.DepartmentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @auther thk
 * @date 2020/10/22 - 10:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class DepartmentDaoTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void deleteDeptTest(){
        int res = departmentDao.deleteDeptById(1);
        System.out.println(res);
    }

    @Test
    public void selectDeptsByLimitAndOffsetTest(){
        List<Department> departments = departmentDao.selectDeptsByLimitAndOffset(0,5);
        System.out.println(departments.size());
        for (int i = 0; i < departments.size(); i++) {
            System.out.println(departments.get(i));
        }
    }

    @Test
    public void selectTotolCount(){
        int i = departmentDao.countDepts();
        System.out.println(i);
    }

    @Test
    public void InsertDept(){
        Department department = new Department();
        department.setDeptId(4);
        department.setDeptName("运营");
        department.setDeptLeader("呵呵");

        int i = departmentDao.insertDept(department);
        System.out.println(i);
    }

    @Test
    public void selectDeptById(){
        Department department = departmentDao.selectDeptById(2);
        System.out.println(department);
    }

    @Test
    public void updateDeptById(){
        Department department = new Department();
        department.setDeptName("维修");
        department.setDeptLeader("呵呵");
        int i = departmentDao.updateDeptById(5, department);
        System.out.println(i);
    }

    @Test
    public void selectDept(){
        List<Department> departmentList = departmentDao.selectDepts();
        for(Department department : departmentList){
            System.out.println(department);
        }
    }

}
