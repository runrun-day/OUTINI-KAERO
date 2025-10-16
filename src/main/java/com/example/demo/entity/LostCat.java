package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.locationtech.jts.geom.Point;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lost_cat")
@Getter
@Setter
public class LostCat {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @Column(name = "cat_title")
    private String catTitle;

    @Column(name = "cat_rename_picture")
    private String catRenamePicture;

    @Column(name = "cat_explanation")
    private String catExplanation;

    @Column(name = "cat_date")
    private LocalDateTime catDate;

    @Column(name = "location", columnDefinition = "POINT SRID 4326")
    private Point location;

    @Column(name = "cat_delete_flag")
    private boolean catDeleteFlag;
    
//    mappedBy:相手側のフィールド名→Messageエンティティのフィールド名
    @OneToMany(mappedBy = "lostCat")
    private List<Message> messages;
    
}
