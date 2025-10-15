package com.example.demo.service;

import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserLocation;
import com.example.demo.repository.UserLocationRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLocationService {

	private final UserRepository userRepository;
    private final UserLocationRepository userLocationRepository;
    
    public void saveUserLocation(String mail, double lat, double lon) {
//        UserAccount user = userRepository.findByMail(mail)
//            .orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));

	    	Optional<UserAccount> optionalUser = userRepository.findByMail(mail);
	
	    	UserAccount user;
	    	
	    	if (optionalUser.isPresent()) {
	    	    user = optionalUser.get();
	    	} else {
	    	    throw new IllegalArgumentException("ユーザーが見つかりません");
	    	}
    	
        UserLocation location = new UserLocation();
        location.setUser(user); // user_id 外部キーと紐付け
        location.setUserLocation(new GeometryFactory(new PrecisionModel(), 4326)
            .createPoint(new Coordinate(lon, lat))); // JTS Point型(経度:longitude 緯度:latitude)
        location.setLocationDeleteFlag(false);

        userLocationRepository.save(location);
    }
}
