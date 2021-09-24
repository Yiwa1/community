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

    List<QuestionDTO> QueryAllQuestion(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    User queryUserByAccountId(String accountId);

    Integer count();

}
