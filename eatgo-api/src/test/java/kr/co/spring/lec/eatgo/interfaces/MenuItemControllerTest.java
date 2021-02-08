package kr.co.spring.lec.eatgo.interfaces;

import kr.co.spring.lec.eatgo.application.MenuItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // spring 을 사용하여 Test running
@WebMvcTest(MenuItemController.class) // RestaurantController Test
public class MenuItemControllerTest {

    @Autowired
    private MockMvc mvc; // Controller Test

    @MockBean
    private MenuItemService menuItemService;

    @Test
    public void bulkUpdate() throws Exception {
        mvc.perform(put("/restaurants/1/menuitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]") // JSON 의 Array 형태로 추가
        ).andExpect(status().isOk());

        verify(menuItemService).bulkUpdate(eq(1L),any());
    }
}