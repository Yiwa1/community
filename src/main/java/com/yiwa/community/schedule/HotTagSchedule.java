package com.yiwa.community.schedule;

import com.yiwa.community.cache.HotTag;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotTagSchedule {

    @Autowired
    QuestionMapper questionMapper;

    @Scheduled(cron = "0 0 7,13,18 * * ?")
    public void updateHotTag(){
        Map<String,Integer> map=new HashMap<>();
        int offset=0;
        List<QuestionDTO> questionList;
        do {
            //每次查询15个
            questionList = questionMapper.queryAllQuestion(offset,15);
            offset=offset+15;
            //priority=5*包含该标签的问题数+评论数
            for (QuestionDTO questionDTO : questionList) {
                String[] tags = questionDTO.getTag().split(",");
                for (String tag : tags) {
                    map.put(tag,map.getOrDefault(tag,0)+5+questionDTO.getCommentCount());
                }
            }
        }while (questionList.size()!=0&&offset<=120);

        PriorityQueue<Map.Entry<String,Integer>> priorityQueue=new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            priorityQueue.add(entry);
        }

        //添加热度前5的标签
        List<Map.Entry<String,Integer>> list=new LinkedList<>();
        while (!priorityQueue.isEmpty()&&list.size()<=4){
            list.add(priorityQueue.poll());
        }

        HotTag.setHotTagEntryList(list);



    }

}
