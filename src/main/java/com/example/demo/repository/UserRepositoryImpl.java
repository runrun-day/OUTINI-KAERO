package com.example.demo.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserAccount;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
//	新規登録
	@Override
	public boolean userSignUpAdd(UserAccount user) {
		String sql = "INSERT INTO user ( user_name,mail,password,login_date) \n"
				+ "VALUES ( ?,?,?,now());";
		
		try {
	        int rows = jdbcTemplate.update(sql,
	        		user.getUserName(),
	        		user.getMail(),
	        		user.getPassword());
	        return rows > 0; // 1件以上挿入できれば true
	    } catch (DataAccessException e) {
	        // ログに残す
	        System.err.println("Insert failed: " + e.getMessage());
	        return false;
	    }
	}

	@Override
	public boolean userMailSearch(String mail) {
		String sql = "SELECT COUNT(*) > 0 FROM user_account WHERE mail = ?;";
//		数値での受け取り
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, mail);
		
//		countが0ならtrue
		return count == 0;
	}
	
}
