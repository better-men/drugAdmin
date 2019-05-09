package com.drug.admin.dao;

import com.drug.admin.common.source.SQLProvider;
import com.drug.admin.entity.RepertoryClass;
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
public class RepertoryClassDao {

    private final static String SQL_REPERTORY_CLASS_LIST = "sql_repertory_class_list";
    private final static String SQL_INSERT_REPERTORY_CLASS = "sql_insert_repertory_class";
    private final static String SQL_DELETE_REPERTORY_CLASS = "sql_delete_repertory_class";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RepertoryClass> getRepertoryClassList(){
        final String sql = SQLProvider.getFromXml(SQL_REPERTORY_CLASS_LIST);
        return jdbcTemplate.query(sql,new RepertoryClassMapper());
    }

    public int insertRepertoryClass(final RepertoryClass repertoryClass){
        final String sql = SQLProvider.getFromXml(SQL_INSERT_REPERTORY_CLASS);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, repertoryClass.getClassId());
                ps.setString(2, repertoryClass.getClassName());
                ps.setString(3, repertoryClass.getClassDesc());
            }
        });
    }

    public int deleteRepertoryClass(final String id){
        final String sql = SQLProvider.getFromXml(SQL_DELETE_REPERTORY_CLASS);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        });
    }

    class RepertoryClassMapper implements RowMapper<RepertoryClass> {
        public RepertoryClass mapRow(ResultSet rs, int rowNum) throws SQLException {
            RepertoryClass repertoryClass = new RepertoryClass();
            repertoryClass.setClassId(rs.getString("class_id"));
            repertoryClass.setClassName(rs.getString("class_name"));
            repertoryClass.setClassDesc(rs.getString("class_desc"));
            return repertoryClass;
        }
    }

}
