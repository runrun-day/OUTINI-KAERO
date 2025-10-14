package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LostCat;

@Repository
public interface LostCatRepository extends JpaRepository<LostCat, Integer> {
//	半径10km内

//	緯度：lat
//	経度：lon
//	4326：WGS84（世界測地系）のSRID（座標系ID）
//	 ST_GeomFromText(CONCAT('POINT(', :lon, ' ', :lat, ')'), 4326)→検索位置
//	lc.location→lost_catテーブルのlocation座標
//	AND lc.cat_delete_flag = false→削除フラグfalseの猫だけ
	
	@Query(value = """
		    SELECT * FROM lost_cat lc
		    WHERE ST_Distance_Sphere(
		        lc.location,
		        ST_GeomFromText(CONCAT('POINT(', :lon, ' ', :lat, ')'), 4326)
		    ) <= 10000
		    AND lc.cat_delete_flag = false
		    """, nativeQuery = true)
		List<LostCat> findNearbyCats(@Param("lat") double lat, @Param("lon") double lon);
}
