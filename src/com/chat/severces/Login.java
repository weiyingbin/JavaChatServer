package com.chat.severces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chat.dao.DAO;

/**
 * @author 作者 魏迎宾 E-mail: weiyingbin2017@outlook.com
 * @date 创建时间：2018年2月15日 下午6:45:57
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class Login {
	public boolean verifyLogin(String line) {
		String[] split = line.split("#");
		/*
		 * System.out.println(split[0]); System.out.println(split[1]);
		 */
		String username = split[0];
		String password = split[1];
		Connection conn = DAO.getConn();
		ResultSet rs = DAO.executeQuery("select*from user");
		try {
			while (rs.next()) {
				String une = rs.getString("username");
				String pwd = rs.getString("password");
				if (username.equals(une) && password.equals(pwd)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("查询失败");
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
