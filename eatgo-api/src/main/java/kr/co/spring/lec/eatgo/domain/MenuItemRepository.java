package kr.co.spring.lec.eatgo.domain;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAllByRestaurantId(long restaurantId);
}
