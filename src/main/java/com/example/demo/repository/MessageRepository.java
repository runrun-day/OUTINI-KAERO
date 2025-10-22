package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Message;
import com.example.demo.entity.UserAccount;

public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query("SELECT m FROM Message m WHERE m.lostCat.user = :user AND m.deleteFlag = false ORDER BY m.messageDate DESC")
    List<Message> findReceivedByUser(@Param("user") UserAccount user);
	
	Optional<Message> findByMessageId(Integer messageId);
	
	@Query("SELECT m FROM Message m WHERE m.fromUser.userId = :userId ORDER BY m.messageDate DESC")
	List<Message> findMessagesByUserId(@Param("userId") Integer userId);
	
}
