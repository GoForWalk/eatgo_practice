package kr.co.spring.lec.eatgo.application;

import kr.co.spring.lec.eatgo.domain.MenuItem;
import kr.co.spring.lec.eatgo.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuItemServiceTest {

//    @Autowired
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        menuItems.add(MenuItem.builder().name("Kimchi").build());
        menuItems.add(MenuItem.builder().id(12L).name("Guk Bob").build());
        menuItems.add(MenuItem.builder().id(1004L).destroy(true).build());

        menuItemService.bulkUpdate(1L, menuItems);

        verify(menuItemRepository, times(1)).saveAll(any());
        verify(menuItemRepository, times(1)).deleteById(eq(1004L));
    }

}