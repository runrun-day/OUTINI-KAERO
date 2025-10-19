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
//  GenerationType.IDENTITY：DB側で自動採番
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

//	ManyToOne:多対1の関係
//  FetchType.LAZY (遅延ロード:外部キー情報は必要に応じてタイプ)
    @ManyToOne
//  外部キーの関連エンティティをどのDBカラムで結びつけるか指定する
//  name：外部キーのカラム名 を明示的に指定
//  nullable = false：外部キーは必ず値が入っていなければならない（NOT NULL制約）
    @JoinColumn(name = "cat_id", nullable = false)
    private LostCat lostCat;

    //メッセージ送信ユーザーの外部キー
    @ManyToOne
//  外部キーの関連エンティティをどのDBカラムで結びつけるか指定する
//  name：外部キーのカラム名 を明示的に指定
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
