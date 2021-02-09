package kr.co.spring.lec.eatgo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll();

//    Optional<Restaurant> findById(long id);

//    Restaurant save(Restaurant restaurant);
}
