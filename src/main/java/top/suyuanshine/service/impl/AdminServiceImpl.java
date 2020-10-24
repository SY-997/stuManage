package top.suyuanshine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import top.suyuanshine.dao.AdminDao;
import top.suyuanshine.domain.TaskFinished;
import top.suyuanshine.domain.User;
import top.suyuanshine.service.AdminService;
import top.suyuanshine.utils.JedisUtils;

import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/28 21:06
 *管理员业务实现
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;


    @Override
    public List<String> findTaskAll() {
        return adminDao.findTaskAll();
    }

    @Override
    public List<User> findUserByTid(int tid) {
        return adminDao.findUserByTid(tid);
    }

    @Override
    public Map<Integer, Integer> find_flag(int t_id) {
        return null;
    }

    @Override
    public void addTask(String taskName) {
        adminDao.addTask(taskName);

    }

    @Override
    public void updateTaskFinished(TaskFinished taskFinished) {
        adminDao.updateTaskFinished(taskFinished);
    }

    @Override
    public void insertTaskFinished(TaskFinished taskFinished) {
        adminDao.insertTaskFinished(taskFinished);

    }

    @Override
    public TaskFinished findByUidWithTid(TaskFinished taskFinished) {
        return adminDao.findByUidWithTid(taskFinished);
    }

    @Override
    public List<User> findFlagByName(int tid, String name) {
        return adminDao.findFlagByName(tid,name);
    }

    @Override
    public void setTid(int tid) {
        Jedis jedis = JedisUtils.getJedis();
            jedis.set("tid",tid+"");
            jedis.close();
    }
}
