package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "message")
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private LostCat lostCat;

    //メッセージ送信ユーザーの外部キー
    @ManyToOne
    @JoinColumn(name = "message_user_id")  
    private UserAccount fromUser;

    @Column(name = "m_title")
    private String title;

    @Column(name = "m_rename_picture")
    private String renamePicture;

    @Column(name = "m_explanation")
    private String explanation;

    @Column(name = "message_date")
    private LocalDateTime messageDate;

    @Column(name = "m_delete_flag")
    private boolean deleteFlag;
}
