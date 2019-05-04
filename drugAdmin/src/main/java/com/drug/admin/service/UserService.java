package com.drug.admin.service;

import com.drug.admin.dao.UserDao;
import com.drug.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> userList(){
        return userDao.userList();
    }

    public User selectUserByAccount(final String userAccount){
        return userDao.selectUserByAccount(userAccount);
    }

    public int insertUser(final User user,final HttpSession session){
        return userDao.insertUser(user,session);
    }

    public int updateUser(final User user) {
        return userDao.updateUser(user);
    }

    public int deleteUser(final String userAccount){
        return userDao.deleteUser(userAccount);
    }

    public List<User> likeUserByAccount(final String userAccount){
        return userDao.likeUserByAccount(userAccount);
    }

}
