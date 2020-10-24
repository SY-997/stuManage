package top.suyuanshine.domain;

import java.io.Serializable;

/**
 * 学生任务完成情况实体类
 */
public class TaskFinished implements Serializable {

  private int uid;//学生id
  private int tid;//任务id
  private int flag;//完成状态，0:为完成，1：完成

  @Override
  public String toString() {
    return "TaskFinished{" +
            "uid=" + uid +
            ", tid=" + tid +
            ", flag=" + flag +
            '}';
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int sid) {
    this.uid = sid;
  }


  public int getTid() {
    return tid;
  }

  public void setTid(int tid) {
    this.tid = tid;
  }


  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

}
