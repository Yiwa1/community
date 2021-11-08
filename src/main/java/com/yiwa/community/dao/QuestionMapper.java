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

    List<QuestionDTO> queryAllQuestion(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    List<QuestionDTO> queryQuestionByAccountId(@Param("accountId")String accountId, @Param("offset")Integer offset, @Param("pageSize")Integer pageSize);

    QuestionDTO queryQuestionById(@Param("id") Integer id);

    User queryUserByAccountId(String accountId);

    List<QuestionDTO> queryQuestionByKeyWord(@Param("search") String search,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

    Integer count();

    Integer countQuestionByAccountId(@Param("accountId")String accountId);

    Integer countSearchResult(@Param("search")String search);

    void createQuestion(Question question);

    void updateQuestion(Question question);

    void incViewCount(Integer id);

    void incCommentCount(@Param("id") int id,@Param("count") Integer count);

}
