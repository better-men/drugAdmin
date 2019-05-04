package com.drug.admin.dao;

import com.drug.admin.common.source.SQLProvider;
import com.drug.admin.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SQL_SELECT_ROLE_BY_USER_ID = "sql_select_role_by_user_id";
    private final static String SQL_ROLE_LIST = "sql_role_list";
    private final static String SQL_INSERT_ROLE = "sql_insert_role";
    private final static String SQL_UPDATE_ROLE = "sql_update_role";
    private final static String SQL_DELETE_ROLE = "sql_delete_role";

    public List<Role> roleList(){
        final String sql = SQLProvider.getFromXml(SQL_ROLE_LIST);
        return jdbcTemplate.query(sql, new RoleRowMapper());
    }

    public Role getRoleByUserId(final String userId){
        final String sql = SQLProvider.getFromXml(SQL_SELECT_ROLE_BY_USER_ID);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userId);
            }
        }, new ResultSetExtractor<Role>() {
            public Role extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Role role = null;
                role.setUserId(userId);
                while (resultSet.next()){
                    role = new Role();
                    role.setMenuName(resultSet.getString(2));
                }
                return role;
            }
        });
    }

    public int insertRole(final Role role){
        final String sql = SQLProvider.getFromXml(SQL_INSERT_ROLE);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,role.getUserId());
                preparedStatement.setString(2,role.getMenuName());
            }
        });
    }

    public int updateRole(final Role role){
        final String sql = SQLProvider.getFromXml(SQL_UPDATE_ROLE);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,role.getMenuName());
                preparedStatement.setString(2,role.getUserId());
            }
        });
    }

    public int deleteRole(final String userId){
        final String sql = SQLProvider.getFromXml(SQL_DELETE_ROLE);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userId);
            }
        });
    }

    class RoleRowMapper implements RowMapper<Role> {

        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setUserId(resultSet.getString("user_id"));
            role.setMenuName(resultSet.getString("menu_name"));
            return role;
        }
    }

}
