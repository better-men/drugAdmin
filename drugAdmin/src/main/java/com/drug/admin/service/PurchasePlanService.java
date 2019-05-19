package com.drug.admin.service;

import com.drug.admin.common.UUIDUtils;
import com.drug.admin.dao.PurchasePlanDao;
import com.drug.admin.dao.RepertoryDao;
import com.drug.admin.entity.PurchasePlan;
import com.drug.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PurchasePlanService {

    @Autowired
    private PurchasePlanDao purchasePlanDao;

    @Autowired
    private RepertoryDao repertoryDao;

    public List<PurchasePlan> getPurchasePlanList(){
        List<PurchasePlan> purchasePlanList =  purchasePlanDao.getPurchasePlanList();
        for (int i = 0;i<purchasePlanList.size();i++){
            purchasePlanList.get(i).setReNum(repertoryDao.getRepertoryById(purchasePlanList.get(i).getRepertoryId()).get(0).getRepertoryNum());
        }
        return purchasePlanList;
    }

    public PurchasePlan getPurchasePlanById(String planId){
        PurchasePlan purchasePlan =  purchasePlanDao.getPurchasePlanById(planId).get(0);
        purchasePlan.setReNum(repertoryDao.getRepertoryById(purchasePlan.getRepertoryId()).get(0).getRepertoryNum());
        return purchasePlan;
    }

    public int insertPurchasePlan(final PurchasePlan purchasePlan, HttpServletRequest request){
        String id = UUIDUtils.uuid();
        purchasePlan.setPlanId(id);
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        purchasePlan.setCreatedTime(f.format(date));
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        purchasePlan.setCreatedBy(user.getUserAccount());
        purchasePlan.setIsDeleted(0);
        purchasePlan.setPurchaseStatus(0);
        purchasePlan.setReNum(repertoryDao.getRepertoryById(purchasePlan.getRepertoryId()).get(0).getRepertoryNum());
        return purchasePlanDao.insertPurchasePlan(purchasePlan);
    }

    public int updatePurchasePlan(final PurchasePlan purchasePlan) {
        purchasePlan.setReNum(repertoryDao.getRepertoryById(purchasePlan.getRepertoryId()).get(0).getRepertoryNum());
        return purchasePlanDao.updatePurchasePlan(purchasePlan);
    }

    public int deletePurchasePlan(final String planId){
        return purchasePlanDao.deletePurchasePlan(planId);
    }

}
