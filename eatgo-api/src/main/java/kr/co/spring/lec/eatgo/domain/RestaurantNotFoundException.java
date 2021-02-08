package kr.co.spring.lec.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException {


    public RestaurantNotFoundException(long wrongId) {

        super("Could not find restaurant " + wrongId);

    }
}
