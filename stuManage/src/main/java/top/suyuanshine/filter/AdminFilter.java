package top.suyuanshine.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 对管理员访问页面进行拦截转发获取信息
 */
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req1 = (HttpServletRequest) req;
        //admin.jsp访问,由SelectInfo给出信息后转发
        req.getRequestDispatcher("admin/infoSelect").forward(req1,resp);
        return;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
