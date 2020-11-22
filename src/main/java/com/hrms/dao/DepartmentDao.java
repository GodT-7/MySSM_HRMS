package com.hrms.dao;

import com.hrms.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther thk
 * @date 2020/10/22 - 10:03
 */
public interface DepartmentDao {

    String TABLE_NAME = "tbl_dept";
    String INSERT_FIELDS = "dept_name,dept_leader";
    String SELECT_FIELDS = "dept_id as 'deptId'," +
            "dept_name as 'deptName'," +
            "dept_leader as 'deptLeader'";

    /**
     * 删除功能，使用注解方法实现
     * @param deptId
     * @return
     */
    @Delete({"DELETE FROM",TABLE_NAME,"WHERE dept_id = #{deptId}"})
    int deleteDeptById(@Param("deptId") Integer deptId);


    /**
     * 得到第offset开始的limit个数据
     * @param offset
     * @param limit
     * @return
     */
    List<Department> selectDeptsByLimitAndOffset(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询总的部门数量
     * @return
     */
    @Select({"select count(*) from",TABLE_NAME})
    int countDepts();

    /**
     * 插入一条部门数据
     * @param department
     * @return
     */
    @Insert({"INSERT INTO",TABLE_NAME,"(",INSERT_FIELDS,")"+
            "VALUES(#{department.deptName},#{department.deptLeader})"})
    int insertDept(@Param("department") Department department);

    @Select({"select ",SELECT_FIELDS," from",TABLE_NAME,"where dept_id = #{deptId}"})
    Department selectDeptById(@Param("deptId") Integer deptId);

    int updateDeptById(@Param("deptId") Integer deptId,
                       @Param("department") Department department);

    @Select({"select ",SELECT_FIELDS,"from",TABLE_NAME})
    List<Department> selectDepts();
}
