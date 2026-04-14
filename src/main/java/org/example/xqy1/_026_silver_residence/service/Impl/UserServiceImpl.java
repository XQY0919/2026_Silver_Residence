package org.example.xqy1._026_silver_residence.service.Impl;

import org.example.xqy1._026_silver_residence.dao.UserRepository;
import org.example.xqy1._026_silver_residence.pojo.User;
import org.example.xqy1._026_silver_residence.service.UserService;
import org.example.xqy1._026_silver_residence.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
            //密码加密
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("未知错误");
        }

    }

    @Override
    public User login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        User existUser = userRepository.findByUsername(username);

        if (existUser == null) {
            throw new RuntimeException("用户名错误");
        }
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!encryptedPassword.equals(existUser.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        existUser.setPassword("***********");
        return existUser;
    }
}
