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

import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "lost_cat")
public class LostCat {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
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
}
