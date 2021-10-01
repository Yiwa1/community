package com.yiwa.community.dao;

import com.yiwa.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User queryUserByToken(String token);

    User queryUserByAccountId(String accountId);

    void addUser(User user);

    void updateUserInfo(User user);
}
