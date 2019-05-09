package com.drug.admin.controller;

import com.drug.admin.common.response.DataResponse;
import com.drug.admin.common.response.ICommandResponse;
import com.drug.admin.entity.Bound;
import com.drug.admin.service.BoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/bound")
public class BoundController {

    @Autowired
    private BoundService boundService;

    @RequestMapping(value = "/allInBound",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ICommandResponse allInBound(){
        return new DataResponse.Builder<List<Bound>>()
                .setResultValue(boundService.getInBoundList()).build();
    }

    @RequestMapping(value = "/allOutBound",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ICommandResponse allOutBound(){
        return new DataResponse.Builder<List<Bound>>()
                .setResultValue(boundService.getOutBoundList()).build();
    }

}
