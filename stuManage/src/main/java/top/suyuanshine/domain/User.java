package top.suyuanshine.domain;

import java.io.Serializable;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/28 17:11
 * 用户信息实体类
 */
public class User implements Serializable {
    private int id;//用户id
    private int sid;//所在分组id
    private String name;//姓名
    private int flag;//任务完成标志，0：未完成，1：完成

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
