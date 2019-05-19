package com.drug.admin.dao;

import com.drug.admin.common.source.SQLProvider;
import com.drug.admin.entity.PurchaseOrder;
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
public class PurchaseOrderDao {

    private final static String SQL_PURCHASE_ORDER_LIST = "sql_purchase_order_list";
    private final static String SQL_PURCHASE_ORDER_INSERT = "sql_purchase_order_insert";
    private final static String SQL_GET_PURCHASE_ORDER_BY_ID = "sql_get_purchase_order_by_id";
    private final static String SQL_PURCHASE_ORDER_UPDATE = "sql_purchase_order_update";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PurchaseOrder> getPurchaseOrderList(){
        final String sql = SQLProvider.getFromXml(SQL_PURCHASE_ORDER_LIST);
        return jdbcTemplate.query(sql,new PurchaseOrderMapper());
    }

    public List<PurchaseOrder> getPurchaseOrderById(final String id){
        final String sql = SQLProvider.getFromXml(SQL_GET_PURCHASE_ORDER_BY_ID);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        }, new PurchaseOrderMapper());
    }

    public int insertPurchaseOrder(final PurchaseOrder purchaseOrder){
        final String sql = SQLProvider.getFromXml(SQL_PURCHASE_ORDER_INSERT);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, purchaseOrder.getOrderId());
                ps.setString(2, purchaseOrder.getPlanId());
                ps.setInt(3, purchaseOrder.getPurchaseNum());
                ps.setString(4,purchaseOrder.getPurchaseDate());
                ps.setString(5,purchaseOrder.getSupplier());
                ps.setString(6,purchaseOrder.getCreatedBy());
                ps.setString(7,purchaseOrder.getCreatedTime());
                ps.setInt(8,purchaseOrder.getIsDeleted());
            }
        });
    }

    public int updatePurchaseOrder(final PurchaseOrder purchaseOrder){
        final String sql = SQLProvider.getFromXml(SQL_PURCHASE_ORDER_UPDATE);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, purchaseOrder.getPlanId());
                ps.setInt(2, purchaseOrder.getPurchaseNum());
                ps.setString(3,purchaseOrder.getPurchaseDate());
                ps.setString(4,purchaseOrder.getSupplier());
                ps.setString(5,purchaseOrder.getCreatedBy());
                ps.setString(6,purchaseOrder.getCreatedTime());
                ps.setInt(7,purchaseOrder.getIsDeleted());
                ps.setString(8, purchaseOrder.getOrderId());
            }
        });
    }

    public int deletePurchasePlan(final String orderId){
        PurchaseOrder purchaseOrder = this.getPurchaseOrderById(orderId).get(0);
        purchaseOrder.setIsDeleted(1);
        return this.updatePurchaseOrder(purchaseOrder);
    }

    class PurchaseOrderMapper implements RowMapper<PurchaseOrder> {
        public PurchaseOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setOrderId(rs.getString("order_id"));
            purchaseOrder.setPlanId(rs.getString("plan_id"));
            purchaseOrder.setPurchaseNum(rs.getInt("purchase_num"));
            purchaseOrder.setPurchaseDate(rs.getString("purchase_date"));
            purchaseOrder.setSupplier(rs.getString("supplier"));
            purchaseOrder.setCreatedBy(rs.getString("created_by"));
            purchaseOrder.setCreatedTime(rs.getString("created_time"));
            return purchaseOrder;
        }
    }

}
