package com.example.demo.repository;

import java.util.Map;

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
		String sql = "SELECT COUNT(*) > 0 FROM user WHERE mail = ?;";
//		数値での受け取り
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, mail);
		
//		countが0ならtrue
		return count == 0;
	}

	@Override
	public UserAccount userAccountSearch(String mail, String password) {
		String sql = "SELECT user_id,user_name,mail FROM user WHERE mail = ? AND password =? AND user_id <> 1;";
		
		// SQLで検索
		Map<String, Object> list 
						= jdbcTemplate.queryForMap(sql, mail,password);
		UserAccount u = new UserAccount();
		u.setId((int)list.get("user_id"));
		u.setUserName((String)list.get("user_name"));
		u.setMail((String)list.get("mail"));

		return u;
	}
	
}
