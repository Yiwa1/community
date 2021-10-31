package com.yiwa.community.controller;

import com.yiwa.community.dao.CommentMapper;
import com.yiwa.community.dao.NotificationMapper;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.dto.NotificationDTO;
import com.yiwa.community.dto.PaginationDTO;
import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.pojo.Comment;
import com.yiwa.community.pojo.Notification;
import com.yiwa.community.pojo.User;
import com.yiwa.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 个人主页
 * @author yiwa
 * @version 1.0
 * @Date: 2021/9/30
 * */
@Controller
public class profileController {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    NotificationService notificationService;

    /**
     * @param action 跳转到个人页面的具体部分
     * */
    @GetMapping("/profile/{action}/{page}/{pageSize}")
    public String toProfile(@PathVariable(value = "action",required = true)String action,
                            @PathVariable(value = "page",required = false)Integer page,
                            @PathVariable(value = "pageSize",required = false)Integer pageSize,
                            HttpServletRequest request,
                            Model model){



        //获取当前登录用户的信息
        User user=(User) request.getSession().getAttribute("user");

        //前往我的问题页面
        if(action.equals("questions")){
            //设定页面初始值
            Integer count = questionMapper.countQuestionByAccountId(user.getAccountId());
            if(page==null||page<1){
                page=1;
            }
            if(pageSize==null){
                pageSize=15;
            }
            if(page>(int)Math.ceil((double)count/pageSize)){
                page=(int)Math.ceil((double)count/pageSize);
            }

            PaginationDTO paginationDTO=new PaginationDTO();
            paginationDTO.setPageSize(pageSize);
            paginationDTO.setPage(page);
            paginationDTO.setTotalCount(count);
            paginationDTO.setPagination();

            Integer offset=(page-1)*pageSize;
            List<QuestionDTO> questions = questionMapper.queryQuestionByAccountId(user.getAccountId(), offset, pageSize);
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("questions",questions);
            model.addAttribute("action",action);
            return "profile";
        }else if(action.equals("replies")){
            //前往我的问题回复页面
            int count = notificationMapper.queryUnReadMessageCount(user.getAccountId());
            if(page==null||page<1){
                page=1;
            }
            if(pageSize==null){
                pageSize=15;
            }
            if(page>(int)Math.ceil((double)count/pageSize)){
                page=(int)Math.ceil((double)count/pageSize);
            }

            PaginationDTO paginationDTO=new PaginationDTO();
            paginationDTO.setPageSize(pageSize);
            paginationDTO.setPage(page);
            paginationDTO.setTotalCount(count);
            paginationDTO.setPagination();

            Integer offset=(page-1)*pageSize;
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("action",action);
            model.addAttribute("UnReadMessageCount", count);
            model.addAttribute("pagination",paginationDTO);

            List<Notification> notifications = notificationMapper.queryNotificationByReceiverId(user.getAccountId(),page,pageSize);
            List<NotificationDTO> notificationDTOs = notifications.stream().map(notification -> {
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setNotifier(userMapper.queryUserByAccountId(notification.getNotifier()));
                notificationDTO.setContent(notification.getContent());
                notificationDTO.setId(notification.getId());
                notificationDTO.setStatus(notification.getStatus());
                notificationDTO.setGmtCreate(notification.getGmtCreate());
                if(notification.getType()==0) {
                    //回答问题
                    notificationDTO.setDescription(questionMapper.queryQuestionById(Math.toIntExact(notification.getRelateId())).getTitle());
                    notificationDTO.setRelateId(notification.getRelateId());
                }else if(notification.getType()==1){
                    Comment comment = commentMapper.queryCommentById(notification.getRelateId());
                    notificationDTO.setDescription(comment.getContent());
                    notificationDTO.setRelateId(comment.getParentId());
                }
                return notificationDTO;
            }).collect(Collectors.toList());
            model.addAttribute("notifications",notificationDTOs);
            return "replies";
        }
        return "profile";
    }

    @ResponseBody
    @PostMapping("/readMessage/{id}")
    public String readMessage(@PathVariable("id") Long id){
        notificationMapper.readMessage(id);
        return "消息已读";
    }
}
