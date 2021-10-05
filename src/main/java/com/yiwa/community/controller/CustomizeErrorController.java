package com.yiwa.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomizeErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(path = ERROR_PATH)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response){
        HttpStatus status = getStatus(request);
        switch (status.value()){
            case 400:
                request.setAttribute("msg","请求格式问题,请联系管理员解决");
                return new ModelAndView("error/404");
            case 403:
                request.setAttribute("msg","没有访问权限");
            case 404:
                request.setAttribute("msg","页面飞走了,试试别的吧");
                return new ModelAndView("error/404");
            case 500:
                return new ModelAndView("error/500");
        }
        return null;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.resolve(code);
        return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }


    public String getErrorPath() {
        return ERROR_PATH;
    }
}
