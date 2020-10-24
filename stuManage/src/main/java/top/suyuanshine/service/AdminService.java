package top.suyuanshine.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.suyuanshine.domain.TaskFinished;
import top.suyuanshine.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/28 21:05
 * 管理员业务层接口
 */
public interface AdminService {
    /**
     * 获取所有发布了的任务
     * @return
     */
    List<String> findTaskAll();

    /**
     * 查询指定任务tid的所有人完成信息
     * @param tid
     * @return
     */
    List<User> findUserByTid(int tid);

    /**
     * 单独查询指定事件t_id的信息
     * @param t_id
     * @return
     */
    Map<Integer,Integer> find_flag(int t_id);

    /**
     * 添加任务,taskName为这个任务名字
     * @param taskName
     * @return
     */
    void addTask(String taskName);

    /**
     * 管理员设置单独用户任务完成情况(第二次修改)
     * @param taskFinished
     * @return
     */
    void updateTaskFinished(TaskFinished taskFinished);

    /**
     * 管理员设置单独用户任务完成情况(第一次添加)
     * @param taskFinished
     * @return
     */
    void insertTaskFinished(TaskFinished taskFinished);

    /**
     * 通过uid和tid查询是否存在记录
     * @param taskFinished
     * @return
     */
    TaskFinished findByUidWithTid(TaskFinished taskFinished);

    /**
     * 通过姓名查询
     * @param name 姓名
     * @param tid 任务tid
     * @return
     */
    List<User> findFlagByName(int tid,String name);

    /**
     * 设置当前任务tid
     * @param tid
     */
    void setTid(int tid);
}
