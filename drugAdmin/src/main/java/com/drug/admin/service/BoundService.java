package com.drug.admin.service;

import com.drug.admin.dao.BoundDao;
import com.drug.admin.entity.Bound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoundService {

    @Autowired
    private BoundDao boundDao;

    public List<Bound> getInBoundList(){
        return boundDao.getInBoundList();
    }

    public List<Bound> getOutBoundList(){
        return boundDao.getOutBoundList();
    }

    /**
     * 新增一条入库记录，库存表根据入库数量增加对应库存的数量
     * @return
     */
    public int insertInBound(final Bound bound){
        return 0;
    }

    /**
     * 新增一条出库记录，库存表根据出库数量减少对应库存的数量
     * @return
     */
    public int insertOutBound(final Bound bound){
        return 0;
    }

}
