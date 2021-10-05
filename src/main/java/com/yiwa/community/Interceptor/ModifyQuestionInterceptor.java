package com.yiwa.community.Interceptor;

import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.exception.CustomizeErrorCode;
import com.yiwa.community.exception.QuestionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 修改问题权限拦截器
 * */
@Service
public class ModifyQuestionInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        int id= Integer.parseInt(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1));
        //检测问题是否存在
        if(questionMapper.queryQuestionById(id)==null){
            throw new QuestionNotFoundException(CustomizeErrorCode.QUESTION_NOT_FOUND.getMessage());
        }

        Cookie[] cookies = request.getCookies();
        Boolean hasToken=false;
        //比较当前登录用户的token和问题发布者的token
        if(cookies==null){
            //这里不判断是否接受cookie,交给登陆时判断
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    hasToken=true;
                    if(!cookie.getValue().equals(questionMapper.queryQuestionById(id).getUser().getToken())){
                        //不一致
                        response.sendRedirect("/question/"+id);
                        return false;
                    }
                }
            }
        }
        if(!hasToken){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }

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
