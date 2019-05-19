package com.drug.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.drug.admin.common.response.DataResponse;
import com.drug.admin.common.response.ICommandResponse;
import com.drug.admin.entity.PurchasePlan;
import com.drug.admin.service.PurchasePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/purchasePlan")
public class PurchasePlanController {

    @Autowired
    private PurchasePlanService purchasePlanService;

    @RequestMapping(value = "/all",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ICommandResponse all(){
        return new DataResponse.Builder<List<PurchasePlan>>()
                .setResultValue(purchasePlanService.getPurchasePlanList()).build();
    }

    @RequestMapping(value = "/getByPlanId",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse getById(@RequestBody JSONObject params){
        String id = params.getString("planId");
        return new DataResponse.Builder<PurchasePlan>()
                .setResultValue(purchasePlanService.getPurchasePlanById(id)).build();
    }

    @RequestMapping(value = "/insert",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse insert(@RequestBody PurchasePlan purchasePlan, HttpServletRequest request){
        int result = purchasePlanService.insertPurchasePlan(purchasePlan,request);
        return new DataResponse.Builder<Integer>()
                .setResultValue(result).build();
    }


    @RequestMapping(value = "/update",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse update(@RequestBody PurchasePlan purchasePlan){
        int result = purchasePlanService.updatePurchasePlan(purchasePlan);
        return new DataResponse.Builder<Integer>()
                .setResultValue(result).build();
    }

    @RequestMapping(value = "/delete",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse delete(@RequestBody JSONObject params){
        String id = params.getString("planId");
        return new DataResponse.Builder<Integer>()
                .setResultValue(purchasePlanService.deletePurchasePlan(id)).build();
    }
}
