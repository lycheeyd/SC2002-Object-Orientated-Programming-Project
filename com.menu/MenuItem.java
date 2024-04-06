package com.menu;

import com.branch.Branch;

public class MenuItem {
    private String name;
    private double price;
    private Branch branch;
    private menuCategory category;

    public MenuItem(String name, double price, Branch branch, menuCategory category) {
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branchid) {
        this.branch = branchid;
    }

    public menuCategory getCategory() {
        return category;
    }

    public void setCategory(menuCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", branch='" + getBranch().getBranchName() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }

}
