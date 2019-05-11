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
    private final static String SQL_BOUND_INSERT = "sql_bound_insert";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RepertoryDao repertoryDao;

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

    public int insertBound(final Bound bound){
        final String sql = SQLProvider.getFromXml(SQL_BOUND_INSERT);
        if (bound.getType()==0){
            repertoryDao.addRepertoryAddAndReduce(bound.getRepertoryId(),false,bound.getBoundNum());
        }else {
            repertoryDao.addRepertoryAddAndReduce(bound.getRepertoryId(),true,bound.getBoundNum());
        }
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, bound.getBoundId());
                preparedStatement.setDouble(2,bound.getPrice());
                preparedStatement.setInt(3,bound.getBoundNum());
                preparedStatement.setString(4,bound.getRepertoryId());
                preparedStatement.setString(5,bound.getAddress());
                preparedStatement.setString(6,bound.getBoundDesc());
                preparedStatement.setString(7,bound.getCreatedBy());
                preparedStatement.setString(8,bound.getCreatedTime());
                preparedStatement.setInt(9,bound.getIsDeleted());
                preparedStatement.setInt(10,bound.getType());
            }
        });
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
            bound.setCreatedBy(rs.getString("bound.created_by"));
            bound.setCreatedTime(rs.getString("bound.created_time"));
            bound.setIsDeleted(rs.getInt("bound.is_deleted"));
            bound.setType(rs.getInt("type"));
            return bound;
        }
    }

}
