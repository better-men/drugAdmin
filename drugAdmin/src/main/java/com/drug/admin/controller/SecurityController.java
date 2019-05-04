package com.drug.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.drug.admin.common.response.DataResponse;
import com.drug.admin.common.response.ICommandResponse;
import com.drug.admin.entity.User;
import com.drug.admin.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
public class SecurityController {

    @Autowired
    private SecurityService securityService;
    private final Logger log = LoggerFactory.getLogger(SecurityController.class);

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse login(@RequestBody JSONObject params, HttpSession session){
        String userAccount = params.getString("userAccount");
        String password = params.getString("password");
        log.debug(String.format("login userAccount : %s,password: %s", userAccount, password));
        if (securityService.login(userAccount,password,session)){
            User user = (User) session.getAttribute("USER_SESSION");
            return new DataResponse.Builder<User>().setResultValue(user).build();
        }else {
            return new DataResponse.Builder<String>(402).setResultValue("用户名或密码错误").build();
        }
    }

    @RequestMapping(value = "/logout", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ICommandResponse logout(HttpSession session){
        //清除session
        session.invalidate();
        return new DataResponse.Builder().build();
    }

    @RequestMapping(value = "/getCurrentUser" ,produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ICommandResponse getCurrentUser(HttpSession session){
        User user = (User) session.getAttribute("USER_SESSION");
        if (null!=user){
            return new DataResponse.Builder<User>().setResultValue(user).build();
        }else {
            return new DataResponse.Builder(402).build();
        }
    }

}
