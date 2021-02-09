package kr.co.spring.lec.eatgo.application;

import kr.co.spring.lec.eatgo.domain.MenuItem;
import kr.co.spring.lec.eatgo.domain.MenuItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuItemService {

    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public void bulkUpdate(long restaurantId, List<MenuItem> menuItems) {
        List<MenuItem> menuItemList = menuItems.stream() // TODO: delete Logic
                .map(menuItem -> {
                    if (menuItem.isDestroy()){
                        menuItemRepository.deleteById(menuItem.getId());
                    }
                    return menuItem;
                })
                .filter(menuItem -> !menuItem.isDestroy())
                .map(menuItem -> {
                    menuItem.setRestaurantId(restaurantId);
                    return menuItem;
                }).collect(Collectors.toList());

        menuItemRepository.saveAll(menuItemList);
    } // bulkUpdate() end

} // MenuItemService end

