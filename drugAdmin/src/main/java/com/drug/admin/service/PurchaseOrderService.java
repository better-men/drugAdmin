package com.drug.admin.service;

import com.drug.admin.common.UUIDUtils;
import com.drug.admin.dao.PurchaseOrderDao;
import com.drug.admin.entity.PurchaseOrder;
import com.drug.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderDao purchaseOrderDao;

    public List<PurchaseOrder> getPurchaseOrderList(){
        return purchaseOrderDao.getPurchaseOrderList();
    }

    public List<PurchaseOrder> getPurchaseOrderById(final String id){
        return purchaseOrderDao.getPurchaseOrderById(id);
    }

    public int insertPurchaseOrder(final PurchaseOrder purchaseOrder, HttpServletRequest request){
        String id = UUIDUtils.uuid();
        purchaseOrder.setOrderId(id);
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        purchaseOrder.setCreatedTime(f.format(date));
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        purchaseOrder.setCreatedBy(user.getUserAccount());
        purchaseOrder.setIsDeleted(0);
        return purchaseOrderDao.insertPurchaseOrder(purchaseOrder);
    }

    public int deletePurchasePlan(final String orderId){
        return purchaseOrderDao.deletePurchasePlan(orderId);
    }

}
