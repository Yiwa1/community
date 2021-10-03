package com.yiwa.community.advice;

import com.yiwa.community.exception.QuestionNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 * @author yiwa
 * @version 1.0
 * @Date: 2021/10/3
 * */
@ControllerAdvice
public class CustomizeExceptionHandler{
    @ExceptionHandler(QuestionNotFoundException.class)
    public ModelAndView handleControllerException(HttpServletRequest request, Throwable ex, Model model) {
        if(ex instanceof QuestionNotFoundException){
            model.addAttribute("msg",ex.getMessage());
        }
        return new ModelAndView("error/404");
    }
}
