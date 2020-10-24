package top.suyuanshine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import top.suyuanshine.domain.TaskFinished;
import top.suyuanshine.domain.User;
import top.suyuanshine.service.UserService;
import top.suyuanshine.utils.JedisUtils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/29 23:46
 * 用户控制层
 */
@RequestMapping("/user")
@Repository
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 获取当前任务tid
     * @return
     */
    private int  getTid() {
        Jedis jedis = JedisUtils.getJedis();
        //如果Redis没有数据,表示管理员未设置任务
        if (jedis.get("tid")==null||"".equals(jedis.get("tid"))){
            //默认设置1
            jedis.set("tid","1");
        }
        //当前任务tid
        int tid=Integer.parseInt(jedis.get("tid"));
        jedis.close();
        return tid;
    }

    /**
     * 首页信息查询
     * @return
     */
    @RequestMapping("/findIndexInfo")
    public @ResponseBody Map<String,Object> findIndexInfo(){

        //当前任务tid
        int tid=getTid();
        Map<String,Object> map=new HashMap<>();
        //当前任务名称
        String tname=userService.findTnameByTid(tid);
        map.put("tname",tname);
        //总的完成进度
        DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        int total=userService.findTaskFinishedTotal(tid);
        String count=decimalFormat.format(total/0.45);//format 返回的是字符串,总数45人,获得百分比
        map.put("count",count+"%");
        map.put("total","完成人数："+total);
        //每组成员完成数量
        for (int sid=1;sid<=8;sid++){
            int stotal=userService.findGroupTotal(tid,sid);
            map.put("s"+sid,stotal);
        }
        return map;
    }

    /**
     * 查询指定sid组信息
     * @param sid
     * @return
     */
    @RequestMapping("/findTaskFinished")
    public String findTaskFinished(Model model,int sid){
        //当前任务tid
        int tid=getTid();
        List<User> users=userService.findBySidWithTid(tid,sid);
        model.addAttribute("users",users);
        return "choose";
    }

    /**
     * 改变完成状态
     * @return
     */
    @RequestMapping(value = "/changeChecked",method = RequestMethod.POST)
    public String changeChecked(@RequestParam(value = "uid[]",required = false) int[] uid){
        if (uid==null){
            //如果没有提交任何数据，则返回首页
            return "redirect:/index.jsp";
        }
        //创建任务对象
        TaskFinished taskFinished=new TaskFinished();
        //当前任务tid
        int tid=getTid();
        //遍历传来的用户id
        for (int id:uid) {
            //设置好提交的信息
            taskFinished.setUid(id);
            taskFinished.setTid(tid);
            taskFinished.setFlag(1);
            //查询记录是否存在
            TaskFinished taskFinished1 = userService.findByTaskFinished(taskFinished);
            if (taskFinished1==null){
                //不存在则插入
                userService.insertTaskFinished(taskFinished);
            }else {
                //存在则更新
                userService.updateTaskFinished(taskFinished);
            }
        }
        return "redirect:/index.jsp";
    }

    /**
     * 任务进度详情
     * @param model
     * @return
     */
    @RequestMapping("/findListInfo")
    public String findListInfo(Model model){
        //当前任务tid
        int tid=getTid();
        //完成人数
        int total=userService.findTaskFinishedTotal(tid);
        //详情列表
        List<User> users=userService.findUserByTid(tid);
        model.addAttribute("total",total);
        model.addAttribute("users",users);
        return "listInfo";
    }
}
