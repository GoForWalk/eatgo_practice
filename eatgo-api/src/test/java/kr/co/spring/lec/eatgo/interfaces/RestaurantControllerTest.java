package kr.co.spring.lec.eatgo.interfaces;

import kr.co.spring.lec.eatgo.application.RestaurantService;
import kr.co.spring.lec.eatgo.domain.MenuItem;
import kr.co.spring.lec.eatgo.domain.Restaurant;
import kr.co.spring.lec.eatgo.domain.RestaurantNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build()
        );

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

        Restaurant restaurant1 = Restaurant.builder()
                .id(1004L).name("Bob zip").address("Seoul")
                .build();

        Restaurant restaurant2 = Restaurant.builder()
                .id(2020L).name("Cyber Food").address("Seoul")
                .build();

        restaurant1.setMenuItems(Arrays.asList(MenuItem.builder().name("Kimchi").build()));
        restaurant2.setMenuItems(Arrays.asList(MenuItem.builder().name("Kimchi").build()));

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

    @Test
    public void detailWithNotExisted() throws Exception{
        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
        .andExpect(content().string("{}"))
        ;
    }

    @Test
    public void createWithValidData() throws Exception {

//        given(restaurantService.addRestaurant(any())).will(invocation -> {
//            Restaurant restaurant = invocation.getArgument(0);
//
//            return Restaurant.builder()
//                    .id(1234L)
//                    .name(restaurant.getName())
//                    .address(restaurant.getAddress())
//                    .build();
//        });

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Beryong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"))
                ;

        verify(restaurantService).addRestaurant(any());
    }// end create()

    @Test
    public void createInvalidData() throws Exception {
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void updateWithValidData() throws Exception {

        mvc.perform(put("/restaurants/1")
            .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Joker Food\",\"address\":\"Seoul\"}")
        ).andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1L, "Joker Food", "Seoul");
    }// end updateWithValidData()

    @Test
    public void updateWithInvalidData() throws Exception {

        mvc.perform(put("/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}")
        ).andExpect(status().isBadRequest());
    } // end updateWithInvalidData()

    @Test
    public void updateWithOutNameInvalidData() throws Exception {

        mvc.perform(put("/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"Seoul\"}")
        ).andExpect(status().isBadRequest());
    } // end updateWithInvalidData()

}