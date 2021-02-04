package kr.co.spring.lec.eatgo.interfaces;

import kr.co.spring.lec.eatgo.application.RestaurantService;
import kr.co.spring.lec.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){

        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable long id){

        Restaurant restaurant = restaurantService.getRestaurant(id);
//        // 기본 정보 + 메뉴 정보
//
//        Restaurant restaurant = restaurantRepository.findById(id);


        return restaurant;
    }


}
