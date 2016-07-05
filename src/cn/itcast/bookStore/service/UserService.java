package cn.itcast.bookStore.service;

import java.sql.SQLException;

import javax.security.auth.login.LoginException;

import cn.itcast.bookStore.dao.UserDao;
import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.exception.RegisterException;

public class UserService {
	private UserDao dao = new UserDao();
	// 注册操作
	public void register(User user) throws RegisterException {
		// 调用dao完成注册操作
		try {
			dao.addUser(user);

			// 发送激活邮件
//			String emailMsg = "感谢您注册网上书城，点击<a href='http://localhost:8080/bookstore/activeUser?activeCode="
//					+ user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>后使用。<br>为保障您的账户安全，请在24小时内完成激活操作";
//			MailUtils.sendMail(user.getEmail(), emailMsg);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("注冊失败");
		}
	}
	// 登录操作
	public User login(String username, String password) throws LoginException {
		try {
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByUsernameAndPassword(username, password);
			//如果找到，还需要确定用户是否为激活用户
			if (user != null) {
				// 只有是激活才能登录成功，否则提示“用户未激活”
				if (user.getState() == 1) {
					return user;
				}
				throw new LoginException("用户未激活");
			}
			throw new LoginException("用户名或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}

	}
	//邮箱验证
		public User getuserbyemail(String emailV) throws RegisterException  {
			try {
				return dao.getuserbyemail(emailV);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RegisterException("通过email查询用户出错!!");
			}
		}
		
		//用户名验证
			public User getuserbyusername(String usernameV) throws RegisterException  {
				try {
					return dao.getuserbyusername(usernameV);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RegisterException("通过username查询用户出错!!");
				}
			}
}
