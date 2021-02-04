package kr.co.spring.lec.eatgo.application;

import kr.co.spring.lec.eatgo.domain.MenuItem;
import kr.co.spring.lec.eatgo.domain.MenuItemRepository;
import kr.co.spring.lec.eatgo.domain.Restaurant;
import kr.co.spring.lec.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Restaurant restaurant = restaurantRepository.findById(id);

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }

}
