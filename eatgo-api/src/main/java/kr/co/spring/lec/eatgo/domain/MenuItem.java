package kr.co.spring.lec.eatgo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
public class MenuItem {

//    private Long id;

    private String name;

    public MenuItem(String name) {
        this.name = name;
    }
}
