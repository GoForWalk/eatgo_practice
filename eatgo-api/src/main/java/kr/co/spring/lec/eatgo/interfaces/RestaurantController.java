package kr.co.spring.lec.eatgo.interfaces;

import kr.co.spring.lec.eatgo.application.RestaurantService;
import kr.co.spring.lec.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("")
    public List<Restaurant> list(){

        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("{id}")
    public Restaurant detail(@PathVariable long id){

        Restaurant restaurant = restaurantService.getRestaurant(id);

//        // 기본 정보 + 메뉴 정보
//
//        Restaurant restaurant = restaurantRepository.findById(id);

        return restaurant;
    }

    @PostMapping("") // Create 되었을 경우 HttpStatus
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant  = Restaurant.builder()
                .name(name)
                .address(address)
                .id(1234L)
                .build();

        restaurantService.addRestaurant(restaurant);

        URI uri = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(uri).body("{}");
    }

//    @PatchMapping("/restaurant/{id}")
    @PutMapping("{id}")
    public String update(@PathVariable("id") long id, @Valid @RequestBody Restaurant resource){

        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = restaurantService.updateRestaurant(id, name, address);

//        return "{" + restaurant.toString() +"}"; // TODO : "{" + update 내용 +"}"
        return "{}";
    }


}
