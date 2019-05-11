package com.drug.admin.dao;

import com.drug.admin.common.source.SQLProvider;
import com.drug.admin.entity.Repertory;
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
public class RepertoryDao {

    private final static String SQL_REPERTORY_LIST = "sql_repertory_list";
    private final static String SQL_SELECT_REPERTORY_BY_NAME = "sql_select_repertory_by_name";
    private final static String SQL_SELECT_REPERTORY_BY_CLASS = "sql_select_repertory_by_class";
    private final static String SQL_INSERT_REPERTORY = "sql_insert_repertory";
    private final static String SQL_UPDATE_REPERTORY_NUM = "sql_update_repertory_num";
    private final static String SQL_SELECT_REPERTORY_BY_ID = "sql_select_repertory_by_id";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Repertory> getRepertoryList(){
        final String sql = SQLProvider.getFromXml(SQL_REPERTORY_LIST);
        return jdbcTemplate.query(sql,new RepertoryMapper());
    }

    public List<Repertory> getRepertoryById(final String id){
        final String sql = SQLProvider.getFromXml(SQL_SELECT_REPERTORY_BY_ID);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        }, new RepertoryMapper());
    }

    public List<Repertory> getRepertoryByName(final String name){
        final String sql = SQLProvider.getFromXml(SQL_SELECT_REPERTORY_BY_NAME);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, name);
            }
        }, new RepertoryMapper());
    }

    public List<Repertory> getRepertoryByClass(final String RepertoryClass){
        final String sql = SQLProvider.getFromXml(SQL_SELECT_REPERTORY_BY_CLASS);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, RepertoryClass);
            }
        }, new RepertoryMapper());
    }

    public int insertRepertory(final Repertory repertory){
        final String sql = SQLProvider.getFromXml(SQL_INSERT_REPERTORY);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, repertory.getRepertoryId());
                ps.setString(2, repertory.getRepertoryName());
                ps.setString(3, repertory.getRepertoryClass());
                ps.setInt(4, repertory.getRepertoryNum());
                ps.setString(5, repertory.getRepertoryDesc());
                ps.setString(6, repertory.getCreatedBy());
                ps.setString(7, repertory.getCreatedTime());
                ps.setInt(8, repertory.getIsDeleted());
            }
        });
    }

    public int addRepertoryAddAndReduce(final String id,final boolean flag,final Integer num) {
        final String sql = SQLProvider.getFromXml(SQL_UPDATE_REPERTORY_NUM);
        final Repertory repertory = this.getRepertoryById(id).get(0);
        if (!flag){
            return jdbcTemplate.update(sql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, repertory.getRepertoryNum()-num);
                    ps.setString(2, id);
                }
            });
        }else {
            return jdbcTemplate.update(sql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, repertory.getRepertoryNum()+num);
                    ps.setString(2, id);
                }
            });
        }
    }

    class RepertoryMapper implements RowMapper<Repertory> {
        public Repertory mapRow(ResultSet rs, int rowNum) throws SQLException {
            Repertory repertory = new Repertory();
            repertory.setRepertoryId(rs.getString("repertory_id"));
            repertory.setRepertoryName(rs.getString("repertory_name"));
            repertory.setRepertoryClass(rs.getString("repertory_class"));
            repertory.setRepertoryNum(rs.getInt("repertory_num"));
            repertory.setRepertoryDesc(rs.getString("repertory_desc"));
            repertory.setCreatedBy(rs.getString("created_by"));
            repertory.setCreatedTime(rs.getString("created_time"));
            repertory.setIsDeleted(rs.getInt("is_deleted"));
            return repertory;
        }
    }

}
