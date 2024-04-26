package com.branch;

import com.cache.EmployeeCache;
import com.users.StaffRole;

/**
 * Branch is a class that stores branch attributes.
 * It contains methods to retreieve the number of staff or managers and check if we can add staff to the branch.
 * @version 1.0
 * @since 2024-04-26
 */

public class Branch {

    /**
     * The name of the branch.
     */
    private BranchName branchName;

    /**
     * The location of the branch.
     */
    private String location;

    /**
     * The staff quota of the branch.
     */
    private int staffQuota;


    /**
     * Constructor for Branch class
     * @param branchName The BranchName of the branch.
     * @param location The location of the branch.
     * @param staffQuota The staff quota of the branch.
     */
    public Branch(BranchName branchName, String location, int staffQuota) {
        this.branchName = branchName;
        this.location = location;
        this.staffQuota = staffQuota;
    }

    
    /** 
     * Retrieves the number of staff in a branch
     * @param cache Calls the Employee Cache
     * @return Returns the number of Staff in the branch.
     */
    public int getStaffCount(EmployeeCache cache){
        return cache.getFilteredItems(this.branchName,StaffRole.S).size();
    }

    /** 
     * Retrieves the number of managers in a branch
     * @param cache Calls the Employee Cache
     * @return Returns the number of Managers in the branch.
     */
    public int getManagerCount(EmployeeCache cache){
        return cache.getFilteredItems(this.branchName,StaffRole.M).size();
    }

    /** 
     * Checks if the staff quota of a branch is reached
     * @param branch The branch to check.
     * @param cache Calls the Employee Cache
     * @return Returns True if the branch staff quota is full.
     */
    public boolean quotaFull(Branch branch, EmployeeCache cache){
        int staffCount = cache.getFilteredItems(branch.getBranchName(), StaffRole.S).size();
        int managerCount = cache.getFilteredItems(branch.getBranchName(), StaffRole.M).size();
        if (staffCount + managerCount == staffQuota){
            System.out.println("error: staff quota has been reached");
            return true;
        }
        return false;
    }

    /**
     * Checks if the manager quota of the branch has been reached.
     * @param branch The branch to check.
     * @param cache Calls the Employee Cache.
     * @return Returns true if the manager quota has been reached, false otherwise.
     */
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

    /**
     * Getter for branch name.
     * @return The branch name of the branch.
     */
    public BranchName getBranchName() {
        return this.branchName;
    }

    /**
     * Setter for branchname.
     * @param branchName the name of this branch.
     */
    public void setBranchName(BranchName branchName) {
        this.branchName = branchName;
    }

    /**
     * Getter for location.
     * @return The location of the branch.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Setter for location.
     * @param location the location of this branch.
     */   
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for staff quota
     * @return The staff quota of the branch.
     */
    public int getStaffQuota() {
        return this.staffQuota;
    }

    /**
     * Setter for branch staff quota.
     * @param staffQuota the staff quota of this branch
     */   
    public void setStaffQuota(int staffQuota) {
        this.staffQuota = staffQuota;
    }

    /**
    * Generates a string representation of the branch attributes
    * 
    * @return The string representation of the branch and its attributes.
    */
    @Override
    public String toString() {
        return "{" +
            " branchName='" + getBranchName() + "'" +
            ", location='" + getLocation() + "'" +
            ", staffQuota='" + getStaffQuota() + "'" +
            "}";
    }

}
