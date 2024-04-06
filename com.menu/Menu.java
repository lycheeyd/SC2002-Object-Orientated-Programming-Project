package com.menu;

import java.util.List;

public class Menu {

    private List<MenuItem> menuList;

    public Menu(List<MenuItem> menuList) {
        this.menuList = menuList;
    }

    public List<MenuItem> getMenuList() {
        return this.menuList;
    }

    public void setMenuList(List<MenuItem> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "{" +
            " menuList='" + getMenuList() + "'" +
            "}";
    }

}
