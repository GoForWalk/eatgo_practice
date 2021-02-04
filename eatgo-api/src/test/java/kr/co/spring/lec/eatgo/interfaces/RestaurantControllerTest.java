package kr.co.spring.lec.eatgo.interfaces;

import kr.co.spring.lec.eatgo.application.RestaurantService;
import kr.co.spring.lec.eatgo.domain.MenuItemRepository;
import kr.co.spring.lec.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.spring.lec.eatgo.domain.RestaurantRepository;
import kr.co.spring.lec.eatgo.domain.RestaurantRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // spring 을 사용하여 Test running
@WebMvcTest(RestaurantController.class) // RestaurantController Test
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc; // Controller Test

    @SpyBean// Controller 에 원하는 객체를 주입할 수 있다.
    private RestaurantService restaurantService;

    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk()) // Http Status : 200(Ok)
                .andExpect(content().string(containsString("\"name\":\"Bob zip\""))) // 포함 값 중에 ("name":"Bob zip")포함
                .andExpect(content().string(containsString("\"id\":1004"))) // 포함 값 중에 ("name":"Bob zip")포함
        ;
    }

    @Test
    public void detail() throws Exception{
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