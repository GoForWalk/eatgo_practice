package kr.co.spring.lec.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@Accessors(chain = true)
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToOne
//    private Restaurant restaurant;

    private long restaurantId;

    @Transient // DB에 넣지 않는다~!!
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) // Json 에서 default = false
    private boolean destroy;
}
