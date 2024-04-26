package com.users;

import com.branch.Branch;

/**
 * The Employee class represents an employee in the system.
 * This is an abstract class and should be extended by concrete employee types.
 */
public abstract class Employee {

    /**
     * The name of the employee.
     */
    protected String name;

    /**
     * The login ID of the employee.
     */
    protected String loginID;

    /**
     * The role of the employee.
     */
    protected StaffRole role;

    /**
     * The gender of the employee.
     */
    protected Gender gender;

    /**
     * The age of the employee.
     */
    protected int age;

    /**
     * The branch which the employee belongs to.
     */
    protected Branch branch;

    /**
     * The password of the employee.
     */
    protected String password;

    /**
     * Constructs a new Employee object with the specified attributes.
     * @param name The name of the employee.
     * @param loginID The login ID of the employee.
     * @param role The role of the employee.
     * @param gender The gender of the employee.
     * @param age The age of the employee.
     * @param branch The branch where the employee works.
     * @param password The password of the employee.
     */
    protected Employee(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        this.name = name;
        this.loginID = loginID;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.password = password;
    }

    // Getters & Setters

    /**
     * Retrieve the name
     * @return The name of the employee.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the employee.
     * @param name The new name of the employee.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieve the login ID
     * @return The login ID of the employee.
     */
    public String getLoginID() {
        return this.loginID;
    }

    /**
     * Sets the login ID of the employee.
     * @param loginID The new login ID of the employee.
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    /**
     * Retrieve the role
     * @return The role of the employee.
     */
    public StaffRole getRole() {
        return this.role;
    }

    /**
     * Sets the role of the employee.
     * @param role The new role of the employee.
     */
    public void setRole(StaffRole role) {
        this.role = role;
    }

    /**
     * Retrieve the gender
     * @return The gender of the employee.
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Sets the gender of the employee.
     * @param gender The new gender of the employee.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Retrieve the age
     * @return The age of the employee.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets the age of the employee.
     * @param age The new age of the employee.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retrieve the branch
     * @return The branch where the employee works.
     */
    public Branch getBranch() {
        return this.branch;
    }

    /**
     * Sets the branch where the employee works.
     * @param branch The new branch of the employee.
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    /**
     * Retrieve the password
     * @return The password of the employee.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password of the employee.
     * @param password The new password of the employee.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the employee.
     * @return A string representation of the employee.
     */
    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", loginID='" + getLoginID() + "'" +
            ", role='" + getRole() + "'" +
            ", gender='" + getGender() + "'" +
            ", age='" + getAge() + "'" +
            ", branch='" + ((this.getBranch() != null) ? this.getBranch().getBranchName() : null) + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

}

