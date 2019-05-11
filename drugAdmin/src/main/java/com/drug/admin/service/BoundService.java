package com.drug.admin.service;

import com.drug.admin.common.UUIDUtils;
import com.drug.admin.dao.BoundDao;
import com.drug.admin.entity.Bound;
import com.drug.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public int insertInBound(final Bound bound, HttpServletRequest request){
        String id = UUIDUtils.uuid();
        bound.setBoundId(id);
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bound.setCreatedTime(f.format(date));
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        bound.setCreatedBy(user.getUserAccount());
        bound.setType(1);
        bound.setIsDeleted(0);
        return boundDao.insertBound(bound);
    }

    /**
     * 新增一条出库记录，库存表根据出库数量减少对应库存的数量
     * @return
     */
    public int insertOutBound(final Bound bound, HttpServletRequest request){
        String id = UUIDUtils.uuid();
        bound.setBoundId(id);
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bound.setCreatedTime(f.format(date));
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        bound.setCreatedBy(user.getUserAccount());
        bound.setType(0);
        bound.setIsDeleted(0);
        return boundDao.insertBound(bound);
    }

}
