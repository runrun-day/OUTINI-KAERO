package com.example.demo.entity;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_location")
@Getter
@Setter
public class UserLocation {

    @Id
//  GenerationType.IDENTITY：DB側で自動採番
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_location_id")
    private Integer locationId;

//	UserAccount（外部キー：user_id）
    @ManyToOne(fetch = FetchType.LAZY)
//  外部キーの関連エンティティをどのDBカラムで結びつけるか指定する
//  name：外部キーのカラム名 を明示的に指定
//  nullable = false：外部キーは必ず値が入っていなければならない（NOT NULL制約）
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    // MySQLのPOINT型
    @Column(name = "user_location", columnDefinition = "POINT SRID 4326", nullable = false)
    private Point userLocation;

    @Column(name = "location_delete_flag", nullable = false)
    private boolean locationDeleteFlag = false;
}
