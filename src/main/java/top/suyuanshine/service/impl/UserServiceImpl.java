package top.suyuanshine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyuanshine.dao.UserDao;
import top.suyuanshine.domain.TaskFinished;
import top.suyuanshine.domain.User;
import top.suyuanshine.service.UserService;

import java.util.List;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/29 23:42
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public String findTnameByTid(int tid) {
        return userDao.findTnameByTid(tid);
    }

    @Override
    public List<User> findUserByTid(int tid) {
        return userDao.findUserByTid(tid);
    }

    @Override
    public int findTaskFinishedTotal(int tid) {
        return userDao.findTaskFinishedTotal(tid);
    }

    @Override
    public List<User> findBySidWithTid(int tid, int sid) {
        return userDao.findBySidWithTid(tid,sid);
    }

    @Override
    public int findGroupTotal(int tid, int sid) {
        return userDao.findGroupTotal(tid,sid);
    }

    @Override
    public void updateTaskFinished(TaskFinished taskFinished) {
        userDao.updateTaskFinished(taskFinished);
    }

    @Override
    public void insertTaskFinished(TaskFinished taskFinished) {

        userDao.insertTaskFinished(taskFinished);
    }

    @Override
    public TaskFinished findByTaskFinished(TaskFinished taskFinished) {
        return userDao.findByTaskFinished(taskFinished);
    }
}
