package kr.co.spring.lec.eatgo.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Restaurant {

    private final String name;
    private final String address;
    private final Long id;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant(String name, String address, Long id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String  getInformation() {
        return name + " in " + address;
    }

    public String getAddress() {
        return address;
    }

    public Long getid() {
        return id;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems){
        for(MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }
    }

}
