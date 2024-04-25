package com.branch;

import com.cache.EmployeeCache;
import com.users.StaffRole;

public class Branch {
    private BranchName branchName;
    private String location;
    private int staffQuota;

    public Branch(BranchName branchName, String location, int staffQuota) {
        this.branchName = branchName;
        this.location = location;
        this.staffQuota = staffQuota;
    }

    public int getStaffCount(EmployeeCache cache){
        return cache.getFilteredItems(this.branchName,StaffRole.S).size();
    }

    public int getManagerCount(EmployeeCache cache){
        return cache.getFilteredItems(this.branchName,StaffRole.M).size();
    }

    public boolean quotaFull(Branch branch, EmployeeCache cache){
        int staffCount = cache.getFilteredItems(branch.getBranchName(), StaffRole.S).size();
        int managerCount = cache.getFilteredItems(branch.getBranchName(), StaffRole.M).size();
        if (staffCount + managerCount == staffQuota){
            System.out.println("error: staff quota has been reached");
            return true;
        }
        return false;
    }

    public boolean managerFull(Branch branch, EmployeeCache cache){
        int staffCount = cache.getFilteredItems(branch.getBranchName(), StaffRole.S).size();
        int managerCount = cache.getFilteredItems(branch.getBranchName(), StaffRole.M).size();
        if (staffCount <= 4 && managerCount == 1) {
            System.out.println("error: manager quota has been reached");
            return true;
        }
        else if (staffCount <=8 && managerCount == 2){
            System.out.println("error: manager quota has been reached");
            return true;
        }
        else if (staffCount <= 15 && managerCount == 3){
            System.out.println("error: manager quota has been reached");
            return true;
        }
        return false;
    }

    // Getters & Setters

    public BranchName getBranchName() {
        return this.branchName;
    }

    public void setBranchName(BranchName branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStaffQuota() {
        return this.staffQuota;
    }

    public void setStaffQuota(int staffQuota) {
        this.staffQuota = staffQuota;
    }

    @Override
    public String toString() {
        return "{" +
            " branchName='" + getBranchName() + "'" +
            ", location='" + getLocation() + "'" +
            ", staffQuota='" + getStaffQuota() + "'" +
            "}";
    }

}
