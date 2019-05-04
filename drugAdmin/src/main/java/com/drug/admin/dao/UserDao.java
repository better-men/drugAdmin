package com.drug.admin.dao;

import com.drug.admin.common.source.SQLProvider;
import com.drug.admin.entity.Role;
import com.drug.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author baiyh
 */
@Repository
@Transactional
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleDao roleDao;

    private final static String SQL_SELECT_USER_BY_USER_ACCOUNT = "sql_select_user_by_user_account";
    private final static String SQL_SELECT_USER_BY_USER_ID = "sql_select_user_by_user_id";
    private final static String SQL_USER_LIST = "sql_user_list";
    private final static String SQL_INSERT_USER = "sql_insert_user";
    private final static String SQL_UPDATE_USER = "sql_update_user";
    private final static String SQL_LIKE_USER_BY_USER_ACCOUNT = "sql_like_user_by_user_account";

    public List<User> userList(){
        final String sql = SQLProvider.getFromXml(SQL_USER_LIST);
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public User selectUserById(final String userId){
        final String sql = SQLProvider.getFromXml(SQL_SELECT_USER_BY_USER_ID);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userId);
            }
        }, new ResultSetExtractor<User>() {
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                User user = null;
                while (resultSet.next()){
                    user = new User();
                    user.setUserId(resultSet.getString(1));
                    user.setUserAccount(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setPhone(resultSet.getString(4));
                    user.setEmail(resultSet.getString(5));
                    user.setRole(resultSet.getString(6));
                    user.setCreator(resultSet.getString(7));
                    user.setCreateTime(resultSet.getString(8));
                    user.setDelete(resultSet.getBoolean(9));
                }
                return user;
            }
        });
    }

    public User selectUserByAccount(final String userAccount){
        final String sql = SQLProvider.getFromXml(SQL_SELECT_USER_BY_USER_ACCOUNT);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userAccount);
            }
        }, new ResultSetExtractor<User>() {
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                User user = null;
                while (resultSet.next()){
                    user = new User();
                    user.setUserId(resultSet.getString(1));
                    user.setUserAccount(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setPhone(resultSet.getString(4));
                    user.setEmail(resultSet.getString(5));
                    user.setRole(resultSet.getString(6));
                    user.setCreator(resultSet.getString(7));
                    user.setCreateTime(resultSet.getString(8));
                    user.setDelete(resultSet.getBoolean(9));
                }
                return user;
            }
        });
    }

    public List<User> likeUserByAccount(final String userAccount){
        final String sql = SQLProvider.getFromXml(SQL_LIKE_USER_BY_USER_ACCOUNT);
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, "%"+userAccount+"%");
            }
        }, new ResultSetExtractor<List<User>>() {
            public List<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<User> userList = new ArrayList<User>();
                User user = null;
                while (resultSet.next()){
                    user = new User();
                    user.setUserId(resultSet.getString(1));
                    user.setUserAccount(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setPhone(resultSet.getString(4));
                    user.setEmail(resultSet.getString(5));
                    user.setRole(resultSet.getString(6));
                    user.setCreator(resultSet.getString(7));
                    user.setCreateTime(resultSet.getString(8));
                    user.setDelete(resultSet.getBoolean(9));
                    userList.add(user);
                }
                return userList;
            }
        });
    }

    public int updateUser(final User user) {
        final String sql = SQLProvider.getFromXml(SQL_UPDATE_USER);
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,user.getUserAccount());
                preparedStatement.setString(2,user.getPassword());
                preparedStatement.setString(3,user.getPhone());
                preparedStatement.setString(4,user.getEmail());
                preparedStatement.setString(5,user.getCreator());
                preparedStatement.setString(6,user.getCreateTime());
                preparedStatement.setBoolean(7,user.getDelete());
                preparedStatement.setString(8,user.getUserId());
            }
        });
    }

    public int deleteUser(final String userAccount){
        User user = this.selectUserByAccount(userAccount);
        user.setDelete(true);
        int userResult =  this.updateUser(user);
        if (userResult>0){
            return roleDao.deleteRole(user.getUserId());
        }else {
            return 0;
        }
    }

    public int insertUser(final User user, final HttpSession session){
        final String sql = SQLProvider.getFromXml(SQL_INSERT_USER);
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        final String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        int userResult =  jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                Date date = new Date();
                User nowUser = (User) session.getAttribute("USER_SESSION");
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                preparedStatement.setString(1,temp);
                preparedStatement.setString(2,user.getUserAccount());
                preparedStatement.setString(3,user.getPassword());
                preparedStatement.setString(4,user.getPhone());
                preparedStatement.setString(5,user.getEmail());
                preparedStatement.setString(6,nowUser.getUserAccount());
                preparedStatement.setString(7,f.format(date));
                preparedStatement.setBoolean(8,false);
            }
        });

        if (userResult>0){
            Role role = new Role();
            role.setUserId(temp);
            return roleDao.insertRole(role);
        }else {
            return 0;
        }
    }


}

class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getString("user_id"));
        user.setUserAccount(resultSet.getString("user_account"));
        user.setPassword(resultSet.getString("password"));
        user.setPhone(resultSet.getString("phone"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(resultSet.getString("sys_role.menu_name"));
        user.setCreator(resultSet.getString("creator"));
        user.setCreateTime(resultSet.getString("create_time"));
        user.setDelete(resultSet.getBoolean("is_delete"));
        return user;
    }
}
