package org.example.xqy1._026_silver_residence.service.Impl;

import org.example.xqy1._026_silver_residence.dao.UserRepository;
import org.example.xqy1._026_silver_residence.pojo.User;
import org.example.xqy1._026_silver_residence.service.UserService;
import org.example.xqy1._026_silver_residence.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(User user) {

        User existUser = userRepository.findByUsername(user.getUsername());
        if (existUser != null) {
            throw new RuntimeException("用户名已存在，请更换！");
        }

        existUser = userRepository.findByEmail(user.getEmail());
        if (existUser != null) {
            throw new RuntimeException("邮箱已被注册，请更换！");
        }

        try {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("未知错误");
        }

    }
}
