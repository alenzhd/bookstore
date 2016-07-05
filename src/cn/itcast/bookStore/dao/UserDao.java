package cn.itcast.bookStore.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.utils.DataSourceUtils;

public class UserDao {
	// 添加用户
	public void addUser(User user) throws SQLException {
		String sql = "insert into user(username,password,gender,email,telephone,introduce,activecode) values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(), user.getPassword(),
				user.getGender(), user.getEmail(), user.getTelephone(),
				user.getIntroduce(), user.getActiveCode());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	//根据用户名与密码查找用户
	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		String sql="select * from user where username=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),username,password);
	}
	//邮箱查找
	public User getuserbyemail(String emailV) throws SQLException {
		DataSource dataSource = DataSourceUtils.getDataSource();
		
		//创建数据操作对象
		QueryRunner queryRunner = new QueryRunner(dataSource);
		//?:通配符 , 后面进行赋值
		String sql = "select * from user where email=? ";
		
		return queryRunner.query(sql, new BeanHandler<User>(User.class),emailV);
	}

	//用户名查找
		public User getuserbyusername(String usernameV) throws SQLException {
			DataSource dataSource = DataSourceUtils.getDataSource();
			
			//创建数据操作对象
			QueryRunner queryRunner = new QueryRunner(dataSource);
			//?:通配符 , 后面进行赋值
			String sql = "select * from user where username=? ";
			
			return queryRunner.query(sql, new BeanHandler<User>(User.class),usernameV);
		}
}
