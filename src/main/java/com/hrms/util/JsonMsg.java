package com.hrms.util;

/**
 * @auther thk
 * @date 2020/10/22 - 10:04
 */

import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;

import java.util.HashMap;
import java.util.Map;

/**
 * 工具类，用来包含前后端的json数据
 */
public class JsonMsg {

    private int code;
    private String msg;
    private Map<String,Object> extendInfo = new HashMap<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, Object> extendInfo) {
        this.extendInfo = extendInfo;
    }

    public static JsonMsg success(){
        JsonMsg res = new JsonMsg();
        res.setCode(100);
        res.setMsg("操作成功");
        return res;
    }

    public static JsonMsg fail(){
        JsonMsg res = new JsonMsg();
        res.setCode(200);
        res.setMsg("操作失败");
        return res;
    }

    public JsonMsg addInfo(String key,Object obj){
        this.extendInfo.put(key,obj);
        return this;
    }

    @Override
    public String toString() {
        return "JsonMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", extendInfo=" + extendInfo +
                '}';
    }
}
