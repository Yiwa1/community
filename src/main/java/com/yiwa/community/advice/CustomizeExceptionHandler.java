package com.yiwa.community.advice;

import com.yiwa.community.exception.*;
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


    @ExceptionHandler({CommentHaveNoTargetException.class, CommentTypeException.class, CommentContentException.class, UserNoLoginException.class})
    @ResponseBody
    public Object handleCommentHaveNoTargetException(HttpServletRequest request,HttpServletResponse response,Throwable ex){
        if(ex instanceof CommentHaveNoTargetException){
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("status",((CommentHaveNoTargetException) ex).getErrorCode());
            resultMap.put("msg",ex.getMessage());
            return resultMap;
        }else if(ex instanceof CommentTypeException){
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("status",((CommentTypeException) ex).getErrorCode());
            resultMap.put("msg",ex.getMessage());
            return resultMap;
        }else if(ex instanceof CommentContentException){
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("status",((CommentContentException) ex).getErrorCode());
            resultMap.put("msg",ex.getMessage());
            return resultMap;
        }else if(ex instanceof UserNoLoginException){
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("status",((UserNoLoginException) ex).getErrorCode());
            resultMap.put("msg",ex.getMessage());
            return resultMap;
        }
        return new ModelAndView("error/404");
    }
}
