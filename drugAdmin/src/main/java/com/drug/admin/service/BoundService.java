package com.drug.admin.service;

import com.drug.admin.entity.Bound;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoundService {

    public List<Bound> getInBoundList(){
        return null;
    }

    public List<Bound> getOutBoundList(){
        return null;
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
