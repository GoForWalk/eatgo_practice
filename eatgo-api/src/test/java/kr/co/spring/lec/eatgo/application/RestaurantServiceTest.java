package kr.co.spring.lec.eatgo.application;

import kr.co.spring.lec.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    private RestaurantRepository restaurantRepository;

    private MenuItemRepository menuItemRepository;

    @Before // test 를 실행하기 전에 반드시 한번씩 실행하게 된다.
    public void setUp(){
        restaurantRepository = new RestaurantRepositoryImpl();
        menuItemRepository = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        assertThat(restaurants.get(0).getid(), is(1004L));
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getid(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(), is("Kimchi"));
    }

}