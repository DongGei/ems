package com.yusael.service;

import com.yusael.entity.User;
import org.springframework.stereotype.Service;

//业务逻辑层
@Service
public interface UserService {
    void register(User user);
    User login(String username, String password);
}

