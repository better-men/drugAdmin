package com.drug.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.drug.admin.common.response.DataResponse;
import com.drug.admin.common.response.ICommandResponse;
import com.drug.admin.entity.Role;
import com.drug.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/selectById",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse getRoleByUserId(@RequestBody JSONObject params){
        String userId = params.getString("userId");
        return new DataResponse.Builder<Role>()
                .setResultValue(roleService.getRoleByUserId(userId)).build();
    }

    @RequestMapping(value = "/update",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse updateRole(@RequestBody Role role){
        int result = roleService.updateRole(role);
        if (result > 0) {
            return new DataResponse.Builder<Integer>().setResultValue(
                    result).build();
        } else {
            return new DataResponse.Builder<Integer>().setResultValue(0)
                    .build();
        }
    }

}
