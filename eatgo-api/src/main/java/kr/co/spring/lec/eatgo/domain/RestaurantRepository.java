package kr.co.spring.lec.eatgo.domain;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> findAll();

    Restaurant findById(long id);
}
