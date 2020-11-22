package com.hrms.service;

import com.hrms.bean.Department;
import com.hrms.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther thk
 * @date 2020/10/22 - 10:03
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public List<Department> getDeptList(int offset, int limit) {
        return departmentDao.selectDeptsByLimitAndOffset(offset,limit);
    }

    public int getDeptCount() {
        return departmentDao.countDepts();
    }

    public int deleteDeptById(Integer deptId) {
        return departmentDao.deleteDeptById(deptId);
    }

    public int addDept(Department department) {
        return departmentDao.insertDept(department);
    }

    public Department getDeptById(Integer deptId) {
        return departmentDao.selectDeptById(deptId);
    }

    public int updateDeptById(Integer deptId,Department dept) {
        return departmentDao.updateDeptById(deptId,dept);
    }

    public List<Department> getDeptName() {
        List<Department> departmentList = departmentDao.selectDepts();
        return departmentList;
    }
}
