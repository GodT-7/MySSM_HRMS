package com.hrms.bean;

import java.io.Serializable;

/**
 * @auther thk
 * @date 2020/10/22 - 10:02
 */
public class Department implements Serializable {

    private Integer deptId;
    private String deptLeader;
    private String deptName;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptLeader() {
        return deptLeader;
    }

    public void setDeptLeader(String deptLeader) {
        this.deptLeader = deptLeader;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptLeader='" + deptLeader + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
