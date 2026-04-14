package org.example.xqy1._026_silver_residence.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.xqy1._026_silver_residence.dao.UserRepository;
import org.example.xqy1._026_silver_residence.pojo.User;
import org.example.xqy1._026_silver_residence.service.UserService;
import org.example.xqy1._026_silver_residence.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:63343")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result Register(@RequestBody User user) {
        log.info("用户注册{}", user);
        try {
            userService.register(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }



    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Result<User> login() {


        return Result.success();
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }


}
