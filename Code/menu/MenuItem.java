package com.menu;

import com.branch.Branch;

/**
 * Represents a menu item with its name, price, branch, and category.
 */
public class MenuItem {

    /**
     * The name of the menu item.
     */
    private String name;

    /**
     * The price of the menu item.
     */
    private double price;

    /**
     * The branch which the menu item belongs to.
     */
    private Branch branch;

    /**
     * The category of the menu item.
     */
    private MenuCategory category;

    /**
     * Constructs a MenuItem object with the given parameters.
     *
     * @param name The name of the menu item.
     * @param price The price of the menu item.
     * @param branch The branch associated with the menu item.
     * @param category The category of the menu item.
     */
    public MenuItem(String name, double price, Branch branch, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
    }

    /** 
     * Retrieves the name of the menu item.
     *
     * @return The name of the menu item.
     */
    public String getName() {
        return name;
    }

    /** 
     * Sets the name of the menu item.
     *
     * @param name The name to set for the menu item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Retrieves the price of the menu item.
     *
     * @return The price of the menu item.
     */
    public double getPrice() {
        return price;
    }

    /** 
     * Sets the price of the menu item.
     *
     * @param price The price to set for the menu item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** 
     * Retrieves the branch associated with the menu item.
     *
     * @return The branch associated with the menu item.
     */
    public Branch getBranch() {
        return branch;
    }

    /** 
     * Sets the branch associated with the menu item.
     *
     * @param branchid The branch to set for the menu item.
     */
    public void setBranch(Branch branchid) {
        this.branch = branchid;
    }

    /** 
     * Retrieves the category of the menu item.
     *
     * @return The category of the menu item.
     */
    public MenuCategory getCategory() {
        return category;
    }

    /** 
     * Sets the category of the menu item.
     *
     * @param category The category to set for the menu item.
     */
    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    /** 
     * Returns a string representation of the menu item.
     *
     * @return A string representation of the menu item.
     */
    @Override
    public String toString() {
        return
            " name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", branch='" + getBranch().getBranchName() + "'" +
            ", category='" + getCategory() + "'";
    }

}
