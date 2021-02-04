package kr.co.spring.lec.eatgo.interfaces;

import kr.co.spring.lec.eatgo.application.RestaurantService;
import kr.co.spring.lec.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // spring 을 사용하여 Test running
@WebMvcTest(RestaurantController.class) // RestaurantController Test
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc; // Controller Test

    @MockBean
    RestaurantService restaurantService;


    // 나머지 객체도 Mock Object 로 대체
    // 실제 RestaurantRepository, MenuItemRepository 를 restaurantService 받는 것 처럼 움직이는 MockBean 으로 대체
//    @SpyBean// Controller 에 원하는 객체를 주입할 수 있다.
//    private RestaurantService restaurantService;
//
//    @SpyBean(RestaurantRepositoryImpl.class)
//    private RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Bob zip", "Seoul" , 1004L));

        // Mockito framework 으로 만든 가짜 객체
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk()) // Http Status : 200(Ok)
                .andExpect(content().string(containsString("\"name\":\"Bob zip\""))) // 포함 값 중에 ("name":"Bob zip")포함
                .andExpect(content().string(containsString("\"id\":1004"))) // 포함 값 중에 ("name":"Bob zip")포함
        ;
    }

    @Test
    public void detail() throws Exception{
        Restaurant restaurant1 = new Restaurant("Bob zip", "Seoul" , 1004L);
        Restaurant restaurant2 = new Restaurant("Cyber Food", "Seoul" , 2020L);
        restaurant1.addMenuItem(new MenuItem("Kimchi"));
        restaurant2.addMenuItem(new MenuItem("Kimchi"));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004")) // id : 1004 일때 Test
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Bob zip\""))) // 포함 값 중에 ("name":"Bob zip")포함
                .andExpect(content().string(containsString("\"id\":1004"))) // 포함 값 중에 ("name":"Bob zip")포함
                .andExpect(content().string(containsString("Kimchi"))) // 포함 값 중에 ("name":"Bob zip")포함
        ;

        mvc.perform(get("/restaurants/2020")) // id : 2020 일때 Test
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Cyber Food\""))) // 포함 값 중에 ("name":"Bob zip")포함
                .andExpect(content().string(containsString("\"id\":2020"))) // 포함 값 중에 ("name":"Bob zip")포함
        ;
    }

}