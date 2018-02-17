package com.chat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {
	@SuppressWarnings("unused")
	public static Connection getConn() {
		try {
			String url = null;
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			url = "jdbc:mysql://localhost/chatinfo?characterEncoding=Utf-8";
			String user = "root";
			String password = "";
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			System.out.println("数据库连接成功！");
			return conn;
		} catch (Exception e) {
			System.out.println("数据库连接失败！");
			return null;
		}
	}
	public static ResultSet executeQuery(String sql) {
		try {
			if (DAO.getConn() == null)
				new DAO();
			return DAO
					.getConn()
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {

		}
	}

	@SuppressWarnings("unused")
	private static int executeUpdate(String sql) {
		try {
			if (DAO.getConn() == null)
				new DAO();
			return DAO.getConn().createStatement().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {

		}
	}

	public void connection() throws ClassNotFoundException {
		DAO.getConn();
	}
}
