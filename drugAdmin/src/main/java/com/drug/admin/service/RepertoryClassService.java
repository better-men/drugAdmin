package com.drug.admin.service;

import com.drug.admin.common.UUIDUtils;
import com.drug.admin.dao.RepertoryClassDao;
import com.drug.admin.entity.RepertoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepertoryClassService {

    @Autowired
    private RepertoryClassDao repertoryClassDao;

    /**
     * 获取所有库存分类
     * @return
     */
    public List<RepertoryClass> getRepertoryClassList(){
        return repertoryClassDao.getRepertoryClassList();
    }

    /**
     * 新增一个库存分类
     * @return
     */
    public int insertRepertoryClass(RepertoryClass repertoryClass){
        String id = UUIDUtils.uuid();
        repertoryClass.setClassId(id);
        return repertoryClassDao.insertRepertoryClass(repertoryClass);
    }

    /**
     * 删除一个库存分类
     * @return
     */
    public int deleteRepertoryClass(String id){
        return repertoryClassDao.deleteRepertoryClass(id);
    }

}
