package com.hrms.controller;

import com.hrms.bean.Employee;
import com.hrms.service.EmployeeService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.InetAddress;
import java.util.List;

/**
 * @auther thk
 * @date 2020/10/22 - 16:52
 */
@Controller
@RequestMapping("/hrms/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping("/getEmpList")
    public ModelAndView getEmpList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("employeePage");;

        //每一页展示的个数
        int limit=5;

        //偏移量，从第几个开始查询
        int offset = (pageNo-1)*limit;

        //查询数据
        List<Employee> employees = service.getEmpList(offset,limit);
        //查询总记录数
        int totalItems = service.getEmpCount();
        //查询总页数
        int totolPages = (totalItems % limit==0)?(totalItems / limit):(totalItems / limit)+1;
        //当前页数
        int curPage = pageNo;

        //将所有数据发送给jsp页面
        mv.addObject("employees",employees)
                .addObject("totalItems",totalItems)
                .addObject("totalPages",totolPages)
                .addObject("curPage",curPage);

        return mv;
    }



    @ResponseBody
    @RequestMapping(value = "/deleteEmp/{empId}",method = RequestMethod.DELETE)
    public JsonMsg deleteEmp(@PathVariable("empId") Integer empId){

        int res = service.deleteEmpById(empId);

        if(res != 1){
            JsonMsg.fail().addInfo("emp_del_error","删除失败");
        }

        return JsonMsg.success();
    }


    @ResponseBody
    @RequestMapping("/checkEmpExists/{empName}")
    public JsonMsg checkEmpExists(@PathVariable("empName") String empName){
        //对输入的姓名与邮箱格式进行验证
        String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!empName.matches(regName)){
            return JsonMsg.fail().addInfo("name_reg_error", "输入姓名为2-5位中文或6-16位英文和数字组合");
        }
        Employee emp = service.getEmpByName(empName);
        if(emp != null){
            return JsonMsg.fail().addInfo("name_reg_error","用户名已重复");
        }else{
            return JsonMsg.success();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addEmp",method = RequestMethod.POST)
    public JsonMsg addEmp(Employee employee){
        int res = service.addEmp(employee);

        if(res != 1){
            return JsonMsg.fail();
        }
        return JsonMsg.success();
    }


    @ResponseBody
    @RequestMapping(value = "/getTotalPages",method = RequestMethod.GET)
    public JsonMsg getTotalPages(){
        int count = service.getEmpCount();
        int limit = 5;
        int totalPages = (count % limit == 0) ? (count / limit):(count / limit)+1;
        return JsonMsg.success().addInfo("totalPages",totalPages);
    }

    @ResponseBody
    @RequestMapping(value = "/getEmpById/{empId}",method = RequestMethod.GET)
    public JsonMsg getEmpById(@PathVariable("empId") Integer empId){
        Employee employee = service.getEmpById(empId);
        if(employee == null){
            return JsonMsg.fail();
        }
        return JsonMsg.success().addInfo("employee",employee);
    }


    @ResponseBody
    @RequestMapping("/updateEmp/{updateEmpId}")
    public JsonMsg updateEmp(@PathVariable("updateEmpId") Integer updateEmpId,Employee employee){
        Employee emp = service.getEmpById(updateEmpId);

        int res = service.updateEmpById(updateEmpId,employee);
        if(res != 1){
            return JsonMsg.fail().addInfo("emp_update_error","修改方法失败");
        }
        return JsonMsg.success();
    }

}
