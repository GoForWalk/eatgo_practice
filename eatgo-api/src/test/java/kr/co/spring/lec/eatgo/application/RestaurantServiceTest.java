package kr.co.spring.lec.eatgo.application;

import kr.co.spring.lec.eatgo.domain.MenuItem;
import kr.co.spring.lec.eatgo.domain.MenuItemRepository;
import kr.co.spring.lec.eatgo.domain.Restaurant;
import kr.co.spring.lec.eatgo.domain.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    // Mock 로 변경
//    private RestaurantService restaurantService;
//
//    private RestaurantRepository restaurantRepository;
//
//    private MenuItemRepository menuItemRepository;

    @Before // test 를 실행하기 전에 반드시 한번씩 실행하게 된다.
    public void setUp(){
//        restaurantRepository = new RestaurantRepositoryImpl();
//        menuItemRepository = new MenuItemRepositoryImpl();

        //Mock 개체 설정
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant
                = new Restaurant("Bob zip", "Seoul", 1004L);
        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(restaurant);
    }

    private void mockMenuItemRepository() {

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
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