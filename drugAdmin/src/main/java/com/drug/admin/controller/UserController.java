package com.drug.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.drug.admin.common.response.DataResponse;
import com.drug.admin.common.response.ICommandResponse;
import com.drug.admin.entity.User;
import com.drug.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author baiyh
 */

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public ICommandResponse listUser(){
        return new DataResponse.Builder<List<User>>()
                .setResultValue(userService.userList()).build();
    }

    @RequestMapping(value = "/selectUserByAccount",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse selectUserByAccount(@RequestBody JSONObject params){
        String userAccount = params.getString("userAccount");
        return new DataResponse.Builder<User>()
                .setResultValue(userService.selectUserByAccount(userAccount)).build();
    }

    @RequestMapping(value = "/likeUserByAccount",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse likeUserByAccount(@RequestBody JSONObject params){
        String userAccount = params.getString("userAccount");
        return new DataResponse.Builder<List<User>>()
                .setResultValue(userService.likeUserByAccount(userAccount)).build();
    }

    @RequestMapping(value = "/insert",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse insertUser(@RequestBody User user, HttpSession session){
        int result = userService.insertUser(user,session);
        if (result > 0) {
            return new DataResponse.Builder<Integer>().setResultValue(
                    result).build();
        } else {
            return new DataResponse.Builder<Integer>().setResultValue(0)
                    .build();
        }
    }

    @RequestMapping(value = "/update",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse updateUser(@RequestBody User user){
        int result = userService.updateUser(user);
        if (result > 0) {
            return new DataResponse.Builder<Integer>().setResultValue(
                    result).build();
        } else {
            return new DataResponse.Builder<Integer>().setResultValue(0)
                    .build();
        }
    }

    @RequestMapping(value = "/delete",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse deleteUser(@RequestBody JSONObject params){
        String userAccount = params.getString("userAccount");
        int result = userService.deleteUser(userAccount);
        if (result > 0) {
            return new DataResponse.Builder<Integer>().setResultValue(
                    result).build();
        } else {
            return new DataResponse.Builder<Integer>().setResultValue(0)
                    .build();
        }
    }

}
