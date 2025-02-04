package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDAOImpl implements UserDAO{
	private Connection conn;
		
	public UserDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public boolean userRegister(User us) {
		boolean f = false;
		try {
			String sql = "insert into users(firstName, lastName, tel, email, password) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, us.getFirstName());
			ps.setString(2, us.getLastName());
			ps.setString(3, us.getTel());
			ps.setString(4, us.getEmail());
			ps.setString(5, us.getPassword());
			int i = ps.executeUpdate();
			if(i==1) {
				f=true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public User userLogin(String email, String pass) {
		User us = null;

		try {
			String sql = "select * from users where email=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				us = new User();
				us.setId(rs.getInt(1));
				us.setFirstName(rs.getString(2));
				us.setLastName(rs.getString(3));
				us.setTel(rs.getString(4));
				us.setEmail(rs.getString(5));
				us.setPassword(rs.getString(6));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return us;

	}

}
