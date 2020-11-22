package com.hrms.service;

import com.hrms.bean.Employee;
import com.hrms.dao.EmployeeDao;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @auther thk
 * @date 2020/10/22 - 10:06
 */
@Service
public class EmployeeService {

    @Autowired //自动注入
    private EmployeeDao employeeDao;

    /**
     * 分页查询
     * @param limit 返回记录最大行数
     * @param offset 返回记录行的偏移量
     * @return 如offset=10，limit=5的时候，就会从数据库第11行记录开始返回5条查询结果，即范围从(offset+1)---(offset+limit)
     */
    public List<Employee> getEmpList(int offset, int limit) {
        return employeeDao.selectByLimitAndOffset(offset,limit);
    }

    public int getEmpCount() {
        return employeeDao.countEmps();
    }

    public int deleteEmpById(Integer deptId) {
        return employeeDao.deleteEmpById(deptId);
    }

    public Employee getEmpByName(String empName) {
        return employeeDao.selectByName(empName);
    }

    public int addEmp(Employee employee) {
        return employeeDao.addEmp(employee);
    }

    public Employee getEmpById(Integer empId) {
        return employeeDao.selectById(empId);
    }

    public int updateEmpById(Integer updateEmpId, Employee employee) {
        return employeeDao.updateEmpById(updateEmpId,employee);
    }
}
