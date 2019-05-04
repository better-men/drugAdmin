package com.drug.admin.service;

import com.drug.admin.dao.RoleDao;
import com.drug.admin.dao.UserDao;
import com.drug.admin.entity.Role;
import com.drug.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;

    public List<Role> roleList(){
        return roleDao.roleList();
    }

    public Role getRoleByUserId(final String userId){
        return roleDao.getRoleByUserId(userId);
    }

    public int updateRole(final Role role){
        return roleDao.updateRole(role);
    }

    public int deleteRole(final String userId){
        return roleDao.deleteRole(userId);
    }
}
