package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", nullable = false)
    private LostCat lostCat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_user_id", nullable = false)
    private UserAccount user;

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
