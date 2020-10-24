package top.suyuanshine.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.suyuanshine.domain.TaskFinished;
import top.suyuanshine.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/28 17:29
 * 管理员持久层接口
 */
@Repository
public interface AdminDao {
    /**
     * 获取所有发布了的任务
     * @return
     */
    @Select("select tname from tasks")
    List<String> findTaskAll();

    /**
     * 查询指定任务tid的所有人完成信息
     * @param tid
     * @return
     */
    @Select("SELECT u.*,tf.flag FROM  user AS u LEFT JOIN taskfinished AS tf ON tf.uid=u.id and tf.tid=#{tid} GROUP BY u.id")
    List<User> findUserByTid(int tid);

    /**
     * 单独查询指定事件t_id的信息
     * @param t_id
     * @return
     */
    @Select("SELECT u.*,tf.flag FROM  user AS u LEFT JOIN taskfinished AS tf ON tf.uid=u.id and tf.tid=#{tid}")
    Map<Integer,Integer> find_flag(int t_id);

    /**
     * 添加任务,taskName为这个任务名字
     * @param taskName
     * @return
     */
    @Insert("insert into tasks values(null,#{taskName})")
    void addTask(String taskName);


    /**
     * 管理员设置单独用户任务完成情况(第二次修改)
     * @param taskFinished
     * @return
     */
    @Update("update taskfinished set flag=#{flag} where tid=#{tid} and uid=#{uid}")
    void updateTaskFinished(TaskFinished taskFinished);

    /**
     * 管理员设置单独用户任务完成情况(第一次添加)
     * @param taskFinished
     * @return
     */
    @Insert("insert into taskfinished values(#{uid},#{tid},#{flag})")
    void insertTaskFinished(TaskFinished taskFinished);

    /**
     * 通过uid和tid查询是否存在记录
     * @param taskFinished
     * @return
     */
    @Select("SELECT * FROM taskfinished WHERE uid=#{uid} AND tid=#{tid}")
    TaskFinished findByUidWithTid(TaskFinished taskFinished);

    /**
     * 通过姓名查询
     * @param name 姓名
     * @param tid 任务tid
     * @return
     */
    @Select("SELECT u.*,tf.flag FROM  user AS u LEFT JOIN taskfinished AS tf ON tf.tid=#{tid} AND u.id=tf.uid WHERE u.`name` LIKE #{name} ")
    List<User> findFlagByName(int tid,String name);
}
