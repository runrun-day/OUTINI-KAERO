package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long>{
	
//	Spring Data JPA
	// メールアドレスで検索
    Optional<UserAccount> findByMail(String mail);
    
    // 存在チェック用
//    該当データが存在→true
    boolean existsByMail(String mail);
    
    

}
