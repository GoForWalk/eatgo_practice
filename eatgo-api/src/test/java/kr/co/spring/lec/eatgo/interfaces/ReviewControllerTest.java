package kr.co.spring.lec.eatgo.interfaces;

import kr.co.spring.lec.eatgo.application.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // spring 을 사용하여 Test running
@WebMvcTest(MenuItemController.class) // RestaurantController Test
public class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void createReview() throws Exception {
        mvc.perform(post("/restaurants/{id}/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        ).andExpect(status().isOk());

        verify(reviewService).createReview(any());
    }

}