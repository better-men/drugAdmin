package com.drug.admin.service;

import com.drug.admin.entity.Repertory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepertoryService {

    /**
     * 获取所有库存信息
     * @return
     */
    public List<Repertory> getRepertoryList(){
        return null;
    }

    /**
     * 根据库存名称筛选库存信息
     * @param name
     * @return
     */
    public List<Repertory> getRepertoryByName(final String name){
        return null;
    }

    /**
     * 根据库存分类筛选库存信息
     * @param RepertoryClass
     * @return
     */
    public List<Repertory> getRepertoryByClass(final String RepertoryClass){
        return null;
    }

    /**
     * 新增一种库存  该操作用于库存表中没有某种库存，要新增该库存时，新增的库存数量为0
     * @param repertory
     * @return
     */
    public int insertRepertory(final Repertory repertory){
        return 0;
    }

}