package kr.co.spring.lec.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurantList = new ArrayList<>();

    public RestaurantRepositoryImpl(){
        restaurantList.add(new Restaurant("Bob zip", "Seoul", 1004L));
        restaurantList.add(new Restaurant("Cyber Food", "Seoul", 2020L));
    }

    @Override
    public List<Restaurant> findAll() {

        return restaurantList;
    }

    @Override
    public Restaurant findById(long id) {

        Restaurant restaurantInfo =restaurantList.stream()
                .filter(restaurant -> restaurant.getid().equals(id))
                .findFirst()
                .orElse(null);

        return restaurantInfo;
    }
}
