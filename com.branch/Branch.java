package com.branch;

import com.cache.EmployeeCache;

public class Branch {
    private BranchName branchName;
    private String location;
    private int staffQuota;
    private boolean branchStatus;

    public Branch(BranchName branchName, String location, int staffQuota) {
        this.branchName = branchName;
        this.location = location;
        this.staffQuota = staffQuota;
    }

//return no. of staff + manager, logic done in UI
    public int getStaffCount(){
        return cache.getFilteredItems(Branch.getBranchName(), StaffRole.S).size();
    }

    public int getManagerCount(){
        return cache.getFilteredItems(Branch.getBranchName(), StaffRole.M).size();
    }

    public boolean quotaFull(Branch branch, EmployeeCache cache){
        int staffCount = cache.getFilteredItems(Branch.getBranchName(), StaffRole.S).size();
        int managerCount = cache.getFilteredItems(Branch.getBranchName(), StaffRole.M).size();
        if (staffCount + managerCount == staffQuota){
            System.out.println("error: staff quota has been reached");
            return true;
        }
        return false;
    }

    public boolean managerFull(Branch branch, EmployeeCache cache){
        int staffCount = cache.getFilteredItems(Branch.getBranchName(), StaffRole.S).size();
        int managerCount = cache.getFilteredItems(Branch.getBranchName(), StaffRole.M).size();
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

    public boolean getBranchStatus(){
        return this.branchStatus;
    }

    public void setBranchStatus(boolean stat){
        this.branchStatus = stat;
    }

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
