package com.drug.admin.dao;

import com.drug.admin.entity.User;
import com.drug.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * @author baiyh
 */
@Repository
@Transactional
public class SecurityDao {

    @Autowired
    private UserService userService;

    public Boolean login(String userAccount,String password,HttpSession session){
        if (null == userAccount||null == password){
            return false;
        }else{
            User user = userService.selectUserByAccount(userAccount);
            if (null != user){
                if (user.getPassword().equals(password)){
                    user.setPassword("");
                    session.setAttribute("USER_SESSION",user);
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
    }

}
