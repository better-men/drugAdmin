package com.drug.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.drug.admin.common.response.DataResponse;
import com.drug.admin.common.response.ICommandResponse;
import com.drug.admin.entity.PurchaseOrder;
import com.drug.admin.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/purchaseOrder")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @RequestMapping(value = "/all",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ICommandResponse all(){
        return new DataResponse.Builder<List<PurchaseOrder>>()
                .setResultValue(purchaseOrderService.getPurchaseOrderList()).build();
    }

    @RequestMapping(value = "/getById",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse getById(@RequestBody JSONObject params){
        String id = params.getString("orderId");
        return new DataResponse.Builder<PurchaseOrder>()
                .setResultValue(purchaseOrderService.getPurchaseOrderById(id).get(0)).build();
    }

    @RequestMapping(value = "/insert",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse insert(@RequestBody PurchaseOrder purchaseOrder, HttpServletRequest request){
        int result = purchaseOrderService.insertPurchaseOrder(purchaseOrder,request);
        return new DataResponse.Builder<Integer>()
                .setResultValue(result).build();
    }

    @RequestMapping(value = "/delete",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ICommandResponse delete(@RequestBody JSONObject params){
        String id = params.getString("orderId");
        return new DataResponse.Builder<Integer>()
                .setResultValue(purchaseOrderService.deletePurchasePlan(id)).build();
    }

}
