package com.yiwa.community.dao;

import com.yiwa.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper {

    void createQuestion(Question question);

}
