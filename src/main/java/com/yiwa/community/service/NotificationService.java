package com.yiwa.community.service;

import com.yiwa.community.dao.NotificationMapper;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.enums.NotificationTypeEnum;
import com.yiwa.community.pojo.Comment;
import com.yiwa.community.pojo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class NotificationService {
    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    QuestionMapper questionMapper;

    public void addCommentNotification(Notification notification,Comment comment){
        int questionId = comment.getParentId();
        QuestionDTO questionDTO = questionMapper.queryQuestionById(questionId);
        String receiver = questionDTO.getCreator();
        notification.setNotifier(comment.getCreator());
        notification.setReceiver(receiver);
        notification.setRelateId((long) questionId);
        if(comment.getType()==0){
            notification.setType(NotificationTypeEnum.ANSWER_QUESTION.getType());
            notification.setContent("回答了");
        }else if(comment.getType()==1){
            notification.setType(NotificationTypeEnum.SUBCOMMENT.getType());
            notification.setContent("评论了");
        }
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setStatus(0);
        notificationMapper.addNotification(notification);
    }
}
