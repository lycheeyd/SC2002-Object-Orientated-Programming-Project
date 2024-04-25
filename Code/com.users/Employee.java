package com.users;

import com.branch.Branch;

public abstract class Employee {
    protected String name;
    protected String loginID;
    protected StaffRole role;
    protected Gender gender;
    protected int age;
    protected Branch branch;
    protected String password;

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
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginID() {
        return this.loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public StaffRole getRole() {
        return this.role;
    }

    public void setRole(StaffRole role) {
        this.role = role;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
