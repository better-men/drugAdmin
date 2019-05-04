package com.drug.admin.service;

import com.drug.admin.dao.SecurityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SecurityService {

    @Autowired
    private SecurityDao securityDao;

    public Boolean login(String userAccount,String password,HttpSession session){
        return securityDao.login(userAccount,password,session);
    }

}
