package com.yiwa.community.controller;

import com.yiwa.community.dao.NotificationMapper;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.dto.PaginationDTO;
import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.pojo.User;
import com.yiwa.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 页面路由
 * @author yiwa
 * @version 1.0
 * @Date: 2021/9/14
 * */

@Controller
public class routerController{

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    NotificationMapper notificationMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "pageSize",defaultValue = "15") Integer pageSize){


        /*
        ************************************************
        * 检测是否有用于登录的token,注意存在伪造token的情况  *
        ************************************************
        */
      Cookie[] cookies = request.getCookies();
      if(cookies!=null) {
          for (Cookie cookie : cookies) {
              if (cookie.getName().equals("token")) {
                  String token = cookie.getValue();
                  User user = userMapper.queryUserByToken(token);
                  if (user != null) {
                      //将用户信息存入session中，用于页面展示信息
                      /*
                      *********************************
                      * 注意session的值共享问题!!!       *
                      * 请求转发session的值将会在页面共享  *
                      * 重定向session的值在新页面为null   *
                      * ********************************/
                      request.getSession().setAttribute("user", user);
                      //查询未读回复数量
                      int count = notificationMapper.queryUnReadMessageCount(user.getAccountId());
                      model.addAttribute("UnReadMessageCount",count);
                      break;
                  }
              }
          }
      }

        //查看所有问题数量
        Integer totalCount=questionMapper.count();

      //注意page异常输入

        if(page<1){
            page=1;
        }else if(page!=1&&page>(int)Math.ceil((double)totalCount/pageSize)){
            page=(int)Math.ceil((double)totalCount/pageSize);
        }

        //设置用于分页的基本参数
        PaginationDTO paginationDTO=new PaginationDTO();
        paginationDTO.setPage(page);
        paginationDTO.setPageSize(pageSize);
        paginationDTO.setTotalCount(totalCount);
        paginationDTO.setPagination();
        //计算偏移量
        Integer offset=(page-1)*pageSize;
        //分页查询
        List<QuestionDTO> questions = questionMapper.queryAllQuestion(offset,pageSize);
        model.addAttribute("questions",questions);
        model.addAttribute("pagination",paginationDTO);

        return "index";
    }

}
