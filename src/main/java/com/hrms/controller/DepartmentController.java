package com.hrms.controller;

import com.hrms.bean.Department;
import com.hrms.bean.Employee;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.util.JsonMsg;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @auther thk
 * @date 2020/10/22 - 16:52
 */
@Controller
@RequestMapping("/hrms/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @RequestMapping(value = "/getDeptList",method = RequestMethod.GET)
    public ModelAndView getEmpList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("departmentPage");

        //每一页展示的个数
        int limit=5;

        //偏移量，从第几个开始查询
        int offset = (pageNo-1)*limit;

        //查询数据
        List<Department> departments = service.getDeptList(offset,limit);
        //查询总记录数
        int totalItems = service.getDeptCount();
        //查询总页数
        int totolPages = (totalItems % limit==0)?(totalItems / limit):(totalItems / limit)+1;
        //当前页数
        int curPage = pageNo;

        //将所有数据发送给jsp页面
        mv.addObject("departments",departments)
                .addObject("totalItems",totalItems)
                .addObject("totalPages",totolPages)
                .addObject("curPageNo",curPage);

        return mv;
    }


    /**
     * 部门删除方法
     * @param deptId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delDept/{deptId}",method = RequestMethod.DELETE)
    public JsonMsg deleteDept(@PathVariable("deptId") Integer deptId){
        int res=0;
        if(deptId > 0){
            res = service.deleteDeptById(deptId);
        }
        if(res!=1){
            return JsonMsg.fail().addInfo("del_dept_error","删除异常");
        }
        return JsonMsg.success();
    }


    /**
     * 部门添加方法
     * @param department
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addDept",method = RequestMethod.PUT)
    public JsonMsg addDept(Department department){
        int res = 0;
        if(department == null)
            return JsonMsg.fail().addInfo("del_dept_error","请传入参数");
        res = service.addDept(department);
        if(res != 1){
            return JsonMsg.fail().addInfo("del_dept_error","保存出现异常");
        }

        return JsonMsg.success();
    }


    /**
     * 插入数据后重新得到总页数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTotalPages",method = RequestMethod.GET)
    public JsonMsg getTotalPages(){
        //1.设置每页展示的数据个数
        int limit = 5;
        //2.查询总数据量
        int count = service.getDeptCount();
        //3.得到总页数
        int totalPages = (count % limit) == 0 ? (count / limit):(count / limit)+1;
        //4.返回数据

        return JsonMsg.success().addInfo("totalPages",totalPages);
    }

    @ResponseBody
    @RequestMapping("/getDeptById/{deptId}")
    public JsonMsg getDeptById(@PathVariable("deptId") Integer deptId){
        Department department = null;
        if(deptId == 0 ){
            return JsonMsg.fail().addInfo("get_dept_error","请输入正确的id");
        }
        department = service.getDeptById(deptId);

        if(department == null){
            return JsonMsg.fail().addInfo("get_dept_error","获取数据失败");
        }

        return JsonMsg.success().addInfo("department",department);
    }


    @ResponseBody
    @RequestMapping(value = "/updateDept/{deptId}",method = RequestMethod.PUT)
    public JsonMsg updateDeptById(@PathVariable("deptId") Integer deptId,Department department){

        Department dept = service.getDeptById(deptId);
        dept.setDeptName(department.getDeptName());
        dept.setDeptLeader(department.getDeptLeader());

        int res = service.updateDeptById(deptId,dept);
        if(res != 1){
            return JsonMsg.fail().addInfo("get_dept_error","修改失败");
        }

        return JsonMsg.success();
    }


    /**
     * 用这个方法查询所有的部门名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDeptName",method = RequestMethod.GET)
    public JsonMsg getDeptName(){

        List<Department> departmentList = service.getDeptName();
        if(departmentList == null){
            return JsonMsg.fail().addInfo("error","获取数据错误啦");
        }
        return JsonMsg.success().addInfo("departmentList",departmentList);
    }
}
