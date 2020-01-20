package cn.henry.web.interceptor;

import cn.henry.domain.system.User;
import com.github.pagehelper.util.StringUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser != null){
            //存在用户session，已登录
            return true;//放行
        }else {
            //不存在用户session，未登录
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            //不放行
            return false;
        }
    }
}
