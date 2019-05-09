package com.drug.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.drug.admin.common.response.DataResponse;
import com.drug.admin.common.response.ICommandResponse;
import com.drug.admin.entity.Repertory;
import com.drug.admin.entity.RepertoryClass;
import com.drug.admin.service.RepertoryClassService;
import com.drug.admin.service.RepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/repertory")
public class RepertoryController {

    @Autowired
    private RepertoryService repertoryService;

    @Autowired
    private RepertoryClassService repertoryClassService;

    @RequestMapping(value = "/all",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ICommandResponse getRepertoryList(){
        return new DataResponse.Builder<List<Repertory>>()
                .setResultValue(repertoryService.getRepertoryList()).build();
    }

    @RequestMapping(value = "/getByName",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse getRepertoryByName(@RequestBody JSONObject params){
        String name = params.getString("name");
        return new DataResponse.Builder<List<Repertory>>()
                .setResultValue(repertoryService.getRepertoryByName(name)).build();
    }

    @RequestMapping(value = "/getByClass",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse getRepertoryByClass(@RequestBody JSONObject params){
        String repertoryClass = params.getString("class");
        return new DataResponse.Builder<List<Repertory>>()
                .setResultValue(repertoryService.getRepertoryByClass(repertoryClass)).build();
    }

    @RequestMapping(value = "/insert",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse insertRepertory(@RequestBody Repertory repertory, HttpServletRequest request){
        int result = repertoryService.insertRepertory(repertory,request);
        return new DataResponse.Builder<Integer>()
                .setResultValue(result).build();
    }

    @RequestMapping(value = "/allClass",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ICommandResponse getRepertoryClassList(){
        return new DataResponse.Builder<List<RepertoryClass>>()
                .setResultValue(repertoryClassService.getRepertoryClassList()).build();
    }

    @RequestMapping(value = "/insertClass",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse insertRepertoryClass(@RequestBody RepertoryClass repertoryClass){
        int result = repertoryClassService.insertRepertoryClass(repertoryClass);
        return new DataResponse.Builder<Integer>()
                .setResultValue(result).build();
    }

    @RequestMapping(value = "/deleteClass",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse deleteRepertoryClass(@RequestBody JSONObject params){
        String classId = params.getString("classId");
        int result = repertoryClassService.deleteRepertoryClass(classId);
        return new DataResponse.Builder<Integer>()
                .setResultValue(result).build();
    }

}
