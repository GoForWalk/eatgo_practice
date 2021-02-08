package kr.co.spring.lec.eatgo.domain.front;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class RestaurantPage {

    private String title;
    private String url;
    private String code;
}
