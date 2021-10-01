package com.yiwa.community.Interceptor;

import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器
 * @author yiwa
 * @version 1.0
 * @Date: 2021/9/28
 * */
@Service
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        User user=null;
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    user = userMapper.queryUserByToken(cookie.getValue());
                    break;
                }
            }
        }

        if(user==null) {
            //用户未登录
            if(cookies!=null) {
                //可以添加cookie
                request.setAttribute("msg", "未登录，点击跳转");
                return false;
            }else {
                //监测用户是否接受我们的cookie
                try {
                    Cookie cookie=new Cookie("test","trust-me");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    request.setAttribute("msg", "未登录,点击跳转");
                }catch (Exception e){
                    //用户不接受Cookie
                    request.setAttribute("msg","请接受我们的cookie请求");
                }
            }
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
        request.getSession().setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
