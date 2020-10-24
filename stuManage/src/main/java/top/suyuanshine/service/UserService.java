package top.suyuanshine.service;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import top.suyuanshine.domain.TaskFinished;
import top.suyuanshine.domain.User;

import java.util.List;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/29 23:41
 */
public interface UserService {

    /**
     * 查询当前任务名称
     * @param tid
     * @return
     */
    String findTnameByTid(int tid);
    /**
     * 查询指定任务tid的所有人完成信息
     * @param tid
     * @return
     */
    List<User> findUserByTid(int tid);

    /**
     * 得到该任务完成的总数
     * @param tid
     * @return
     */
    int findTaskFinishedTotal(int tid);

    /**
     * 查询每组完成任务的情况
     * @param tid
     * @param sid
     * @return
     */
    List<User> findBySidWithTid(int tid, int sid);

    /**
     * 每组完成总数
     * @param tid
     * @param sid
     * @return
     */
    int findGroupTotal(int tid,int sid);

    /**
     * 负责人提交更改用户任务完成状态（已被设置过）
     * @param taskFinished
     * @return
     */
    void updateTaskFinished(TaskFinished taskFinished);

    /**
     * 负责人提交更改用户任务完成状态（未被设置过）
     * @param taskFinished
     * @return
     */
    void insertTaskFinished(TaskFinished taskFinished);

    /**
     * 通过uid和tid查询是否存在记录
     * @param taskFinished
     * @return
     */
    TaskFinished findByTaskFinished(TaskFinished taskFinished);
}
