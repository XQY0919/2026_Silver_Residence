package org.example.xqy1._026_silver_residence.service;


import org.example.xqy1._026_silver_residence.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    User login(User user);
}
