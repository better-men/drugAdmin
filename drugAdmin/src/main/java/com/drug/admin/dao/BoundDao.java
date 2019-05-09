package com.drug.admin.dao;

import com.drug.admin.common.source.SQLProvider;
import com.drug.admin.entity.Bound;
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
public class BoundDao {

    private final static String SQL_BOUND_LIST = "sql_bound_list";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Bound> getInBoundList(){
        final String sql = SQLProvider.getFromXml(SQL_BOUND_LIST);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, 1);
            }
        }, new BoundMapper());
    }

    public List<Bound> getOutBoundList(){
        final String sql = SQLProvider.getFromXml(SQL_BOUND_LIST);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, 0);
            }
        }, new BoundMapper());
    }

    class BoundMapper implements RowMapper<Bound> {
        public Bound mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bound bound = new Bound();
            bound.setBoundId(rs.getString("bound_id"));
            bound.setPrice(rs.getDouble("price"));
            bound.setBoundNum(rs.getInt("bound_num"));
            bound.setRepertoryId(rs.getString("bound.repertory_id"));
            bound.setRepertoryName(rs.getString("repertory_name"));
            bound.setRepertoryClass(rs.getString("repertory_class"));
            bound.setRepertoryDesc(rs.getString("repertory_desc"));
            bound.setAddress(rs.getString("address"));
            bound.setBoundDesc(rs.getString("bound_desc"));
            bound.setCreatedBy(rs.getString("created_by"));
            bound.setCreatedTime(rs.getString("created_time"));
            bound.setIsDeleted(rs.getInt("is_deleted"));
            bound.setType(rs.getInt("type"));
            return bound;
        }
    }

}
