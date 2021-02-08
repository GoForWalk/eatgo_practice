package kr.co.spring.lec.eatgo.application;

import kr.co.spring.lec.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository){
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();

        return restaurantList;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


//    public Restaurant updateRestaurant(Long id, String name, String address){
//
//        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
//
//        return restaurant.map(updateRestaurant -> {
//
//            updateRestaurant.setName(name);
//            updateRestaurant.setAddress(address);
//
//            return updateRestaurant;
//
//        }).map(newRestaurant -> restaurantRepository.save(newRestaurant))
//                .orElse(null);
//    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address){
        // Transactional 적용
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        return restaurant.map(updateRestaurant -> {

            updateRestaurant.setName(name);
            updateRestaurant.setAddress(address);

            return updateRestaurant;

        }).orElse(null);
    }

}
