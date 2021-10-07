package com.yiwa.community.advice;

import com.yiwa.community.exception.CommentHaveNoTargetException;
import com.yiwa.community.exception.QuestionNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理
 * @author yiwa
 * @version 1.0
 * @Date: 2021/10/3
 * */
@ControllerAdvice
public class CustomizeExceptionHandler{

    @ExceptionHandler(QuestionNotFoundException.class)
    public ModelAndView handleQuestionNotFoundException(HttpServletRequest request, HttpServletResponse response, Throwable ex, Model model) {
        if(ex instanceof QuestionNotFoundException){
            model.addAttribute("msg",ex.getMessage());
            return new ModelAndView("error/404");
        }
        return null;
    }


    @ExceptionHandler(CommentHaveNoTargetException.class)
    @ResponseBody
    public Object handleCommentHaveNoTargetException(HttpServletRequest request,HttpServletResponse response,Throwable ex){
        if(ex instanceof CommentHaveNoTargetException){
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put(((CommentHaveNoTargetException) ex).getErrorCode(),ex.getMessage());
            return resultMap;
        }
        return new ModelAndView("error/404");
    }
}
