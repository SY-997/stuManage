package top.suyuanshine.filter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @PROJECT_NAME: stuManageSSM
 * @Author: Mr.Y
 * @Create: 2020/6/30 13:57
 * 过滤请求信息不完整的访问
 */
public class ChooseInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //参数正确
        if(request.getQueryString().indexOf("sid=")>=0){
            return true;
        }
        //否则重定向到首页
        response.sendRedirect(request.getContextPath()+"/index.jsp");
        return false;
    }
}
