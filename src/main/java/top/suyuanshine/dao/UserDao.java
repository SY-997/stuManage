package top.suyuanshine.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import top.suyuanshine.domain.TaskFinished;
import top.suyuanshine.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/29 21:17
 * 用户持久层
 */
@Repository
@RequestMapping("/user")
public interface UserDao {

    /**
     * 查询当前任务名称
     * @param tid
     * @return
     */
    @Select("select tname from tasks where id=#{tid}")
    String findTnameByTid(int tid);

    /**
     * 查询指定任务tid的所有人完成信息
     * @param tid
     * @return
     */
    @Select("SELECT u.*,tf.flag FROM  user AS u LEFT JOIN taskfinished AS tf ON tf.uid=u.id and tf.tid=#{tid} GROUP BY u.id")
    List<User> findUserByTid(int tid);

    /**
     * 得到该任务完成的总数
     * @param tid
     * @return
     */
    @Select("SELECT COUNT(*) FROM taskfinished WHERE tid=#{tid} ")
    int findTaskFinishedTotal(int tid);

    /**
     * 查询每组完成任务的情况
     * @param tid
     * @param sid
     * @return
     */
    @Select("SELECT u.*,tf.flag FROM  user AS u LEFT JOIN taskfinished AS tf ON tf.uid=u.id and tf.tid=#{tid} WHERE u.sid=#{sid} GROUP BY u.id")
    List<User> findBySidWithTid(@Param("tid") int tid, @Param("sid") int sid);

    /**
     * 每组完成总数
     * @param tid
     * @param sid
     * @return
     */
    @Select("SELECT count(*) FROM  user AS u LEFT JOIN taskfinished AS tf ON tf.uid=u.id and tf.tid=#{tid} WHERE u.sid=#{sid} AND tf.flag=1")
    int findGroupTotal(@Param("tid") int tid, @Param("sid") int sid);

    /**
     * 负责人提交更改用户任务完成状态（已被设置过）
     * @param taskFinished
     * @return
     */
    @Update("update taskfinished set flag=#{flag} where tid=#{tid} and uid=#{uid}")
    void updateTaskFinished(TaskFinished taskFinished);

    /**
     * 负责人提交更改用户任务完成状态（未被设置过）
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
    TaskFinished findByTaskFinished(TaskFinished taskFinished);

}
