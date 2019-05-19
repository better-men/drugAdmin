package com.drug.admin.dao;

import com.drug.admin.common.source.SQLProvider;
import com.drug.admin.entity.PurchasePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class PurchasePlanDao {

    private final static String SQL_PURCHASE_PLAN_LIST = "sql_purchase_plan_list";
    private final static String SQL_PURCHASE_PLAN_INSERT = "sql_purchase_plan_insert";
    private final static String SQL_GET_PURCHASE_PLAN_BY_ID = "sql_get_purchase_plan_by_id";
    private final static String SQL_PURCHASE_PLAN_UPDATE = "sql_purchase_plan_update";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PurchasePlan> getPurchasePlanList(){
        final String sql = SQLProvider.getFromXml(SQL_PURCHASE_PLAN_LIST);
        return jdbcTemplate.query(sql,new PurchasePlanMapper());
    }

    public List<PurchasePlan> getPurchasePlanById(final String id){
        final String sql = SQLProvider.getFromXml(SQL_GET_PURCHASE_PLAN_BY_ID);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        }, new PurchasePlanMapper());
    }

    public int insertPurchasePlan(final PurchasePlan purchasePlan){
        final String sql = SQLProvider.getFromXml(SQL_PURCHASE_PLAN_INSERT);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, purchasePlan.getPlanId());
                ps.setString(2, purchasePlan.getRepertoryId());
                ps.setInt(3, purchasePlan.getPlanNum());
                ps.setInt(4,purchasePlan.getReNum());
                ps.setString(5,purchasePlan.getPlanDate());
                ps.setInt(6,purchasePlan.getPurchaseStatus());
                ps.setString(7,purchasePlan.getCreatedBy());
                ps.setString(8,purchasePlan.getCreatedTime());
                ps.setInt(9,purchasePlan.getIsDeleted());
            }
        });
    }

    public int updatePurchasePlan(final PurchasePlan purchasePlan){
        final String sql = SQLProvider.getFromXml(SQL_PURCHASE_PLAN_UPDATE);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, purchasePlan.getRepertoryId());
                ps.setInt(2, purchasePlan.getPlanNum());
                ps.setInt(3,purchasePlan.getReNum());
                ps.setString(4,purchasePlan.getPlanDate());
                ps.setInt(5,purchasePlan.getPurchaseStatus());
                ps.setString(6,purchasePlan.getCreatedBy());
                ps.setString(7,purchasePlan.getCreatedTime());
                ps.setInt(8,purchasePlan.getIsDeleted());
                ps.setString(9, purchasePlan.getPlanId());
            }
        });
    }

    public int deletePurchasePlan(final String planId){
        PurchasePlan purchasePlan = this.getPurchasePlanById(planId).get(0);
        purchasePlan.setIsDeleted(1);
        return this.updatePurchasePlan(purchasePlan);
    }

    class PurchasePlanMapper implements RowMapper<PurchasePlan> {
        public PurchasePlan mapRow(ResultSet rs, int rowNum) throws SQLException {
            PurchasePlan purchasePlan = new PurchasePlan();
            purchasePlan.setPlanId(rs.getString("plan_id"));
            purchasePlan.setRepertoryId(rs.getString("repertory_id"));
            purchasePlan.setPlanNum(rs.getInt("plan_num"));
            purchasePlan.setReNum(rs.getInt("re_num"));
            purchasePlan.setPlanDate(rs.getString("plan_date"));
            purchasePlan.setPurchaseStatus(rs.getInt("purchase_status"));
            purchasePlan.setCreatedBy(rs.getString("created_by"));
            purchasePlan.setCreatedTime(rs.getString("created_time"));
            return purchasePlan;
        }
    }

}
