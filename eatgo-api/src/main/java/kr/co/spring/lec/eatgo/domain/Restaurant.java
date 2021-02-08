package kr.co.spring.lec.eatgo.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;
//    private String regionName;
//    private String CategoryName;
//    private String tagNames;


//    @OneToMany
    @Transient
    private List<MenuItem> menuItems;

    public String  getInformation() {
        return name + " in " + address;
    }

    public void setMenuItems(List<MenuItem> menuItems){
        if (menuItems == null){
            menuItems = new ArrayList<>();
        }
        this.menuItems = new ArrayList<>(menuItems);
    }

}
