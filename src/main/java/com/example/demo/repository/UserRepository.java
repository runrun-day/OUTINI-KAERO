package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long>{
	
//	Spring Data JPA
//	メールアドレスで検索
    Optional<UserAccount> findByMail(String mail);
    
//	管理者(user_id=1固定)
    @Query("SELECT u FROM UserAccount u WHERE u.mail = :mail AND u.userId = 1")
    Optional<UserAccount> findAdminByMail(@Param("mail") String mail);

//	ユーザー(user_id != 1)
    @Query("SELECT u FROM UserAccount u WHERE u.mail = :mail AND u.userId <> 1")
    Optional<UserAccount> findUserByMail(@Param("mail") String mail);
    
//	存在チェック用
//   該当データが存在→true
    boolean existsByMail(String mail);
    
 // userDeleteFlag = falseのユーザーをloginDate降順(新しい順)で取得
    List<UserAccount> findByUserDeleteFlagFalseOrderByLoginDateDesc();
    
    Optional<UserAccount> findByUserId(Integer userId);

}
