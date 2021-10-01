package com.yiwa.community.dao;

import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.pojo.Question;
import com.yiwa.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    void createQuestion(Question question);

    List<QuestionDTO> queryAllQuestion(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    User queryUserByAccountId(String accountId);

    Integer count();

    List<QuestionDTO> queryQuestionByAccountId(@Param("accountId")String accountId, @Param("offset")Integer offset, @Param("pageSize")Integer pageSize);

    Integer countQuestionByAccountId(@Param("accountId")String accountId);

    QuestionDTO queryQuestionById(@Param("id") Integer id);

    void updateQuestion(Question question);

}
