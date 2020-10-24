package top.suyuanshine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import top.suyuanshine.domain.TaskFinished;
import top.suyuanshine.domain.User;
import top.suyuanshine.service.AdminService;
import top.suyuanshine.utils.JedisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/29 11:16
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 管理员首页访问，返回相应信息
     * @return
     */
    @RequestMapping("/infoSelect")
    public String infoSelect(Model model){
        Jedis jedis = JedisUtils.getJedis();
        //如果Redis没有数据,表示管理员未设置任务
        if (jedis.get("tid")==null||"".equals(jedis.get("tid"))){
            //默认设置1
            jedis.set("tid","1");
        }
        //当前任务tid
        int tid=Integer.parseInt(jedis.get("tid"));
        jedis.close();
        model.addAttribute("tid",tid);
        //任务列表
        List<String> tasks=adminService.findTaskAll();
        model.addAttribute("tasks",tasks);
        //当前任务完成情况
        List<User> users=adminService.findUserByTid(tid);
        model.addAttribute("users",users);
        //遍历集合，获取完成总数
        int count=0;
        for (User user:users) {
            count+=user.getFlag();
        }
        model.addAttribute("count",count);
        return "admin";
    }

    /**
     * 根据任务tid查询完成情况
     * @param tid
     * @return
     */
    @RequestMapping("/infoSelectByTid")
    public @ResponseBody List<User> infoSelectByTid(int tid){
        List<User> users=adminService.findUserByTid(tid);
        return users;
    }

    /**
     * 更新用户任务完成状态
     * @param taskFinished
     */
    @RequestMapping("/updateTaskFinished")
    public @ResponseBody Map<String,String> updateTaskFinished(TaskFinished taskFinished){
        System.out.println(taskFinished);
        Map<String,String> map=new HashMap<>();
        //先查询是否存在该条记录
        TaskFinished taskFinished1 = adminService.findByUidWithTid(taskFinished);
        if (taskFinished1==null){
            //不存在则插入记录
            adminService.insertTaskFinished(taskFinished);
            map.put("Flag","1");
            return map;
        }else {
            //存在则更新修改
            adminService.updateTaskFinished(taskFinished);
            map.put("Flag","1");
            return map;
        }
    }

    /**
     * 添加任务名称
     * @param tname
     * @return
     */
    @RequestMapping("/addTask")
    public String addTask(String tname){
        adminService.addTask(tname);
        return "redirect:/admin.jsp";
    }

    /**
     * 设置当前任务tid
     * @param tid
     * @return
     */
    @RequestMapping("/setTid/{tid}")
    public String setTid(@PathVariable(value = "tid") int tid){
        adminService.setTid(tid);
        return "redirect:/admin.jsp";
    }

}
