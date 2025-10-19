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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inquiry")
@Getter
@Setter
public class Inquiry {
    @Id
//  GenerationType.IDENTITY：DB側で自動採番
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Integer inquiryId;
//	ManyToOne:多対1の関係
//  FetchType.LAZY (遅延ロード:外部キー情報は必要に応じてタイプ)
    @ManyToOne(fetch = FetchType.LAZY)
//  外部キーの関連エンティティをどのDBカラムで結びつけるか指定する
//  name：外部キーのカラム名 を明示的に指定
//  nullable = false：外部キーは必ず値が入っていなければならない（NOT NULL制約）
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @Column(name = "i_title")
    private String title;

    @Column(name = "i_explanation")
    private String explanation;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "i_delete_flag")
    private boolean deleteFlag;
}
