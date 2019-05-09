package com.drug.admin.service;

import com.drug.admin.common.UUIDUtils;
import com.drug.admin.dao.RepertoryDao;
import com.drug.admin.entity.Repertory;
import com.drug.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RepertoryService {

    @Autowired
    private RepertoryDao repertoryDao;

    /**
     * 获取所有库存信息
     * @return
     */
    public List<Repertory> getRepertoryList(){
        return repertoryDao.getRepertoryList();
    }

    /**
     * 根据库存名称筛选库存信息
     * @param name
     * @return
     */
    public List<Repertory> getRepertoryByName(final String name){
        return repertoryDao.getRepertoryByName(name);
    }

    /**
     * 根据库存分类筛选库存信息
     * @param repertoryClass
     * @return
     */
    public List<Repertory> getRepertoryByClass(final String repertoryClass){
        return repertoryDao.getRepertoryByClass(repertoryClass);
    }

    /**
     * 新增一种库存  该操作用于库存表中没有某种库存，要新增该库存时，新增的库存数量为0
     * @param repertory
     * @return
     */
    public int insertRepertory(final Repertory repertory, HttpServletRequest request){
        String id = UUIDUtils.uuid();
        repertory.setRepertoryId(id);
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        repertory.setCreatedTime(f.format(date));
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        repertory.setCreatedBy(user.getUserAccount());
        repertory.setIsDeleted(0);
        return repertoryDao.insertRepertory(repertory);
    }

    /**
     * 库存数量增加或减少
     * @param id
     * @param flag
     * @return
     */
    public int addRepertoryAddAndReduce(final String id,final boolean flag){
        return repertoryDao.addRepertoryAddAndReduce(id,flag);
    }

}
