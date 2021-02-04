package kr.co.spring.lec.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {

    private List<MenuItem> menuItemList = new ArrayList<>();

    public MenuItemRepositoryImpl(){
        menuItemList.add(new MenuItem("Kimchi"));
    }

    @Override
    public List<MenuItem> findAllByRestaurantId(long restaurantId) {

//        List<MenuItem> menuItems = menuItemList.stream()
//                .filter(menuItemInfo -> menuItemInfo.getId().equals(restaurantId))
//                .collect(Collectors.toList())
//                ;

        return menuItemList;
    }
}
