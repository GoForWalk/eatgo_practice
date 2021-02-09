package kr.co.spring.lec.eatgo.interfaces;

import kr.co.spring.lec.eatgo.application.MenuItemService;
import kr.co.spring.lec.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(
            @PathVariable long restaurantId,
            @RequestBody List<MenuItem> menuItems){

        menuItemService.bulkUpdate(restaurantId, menuItems);

        return "";
    }

}
