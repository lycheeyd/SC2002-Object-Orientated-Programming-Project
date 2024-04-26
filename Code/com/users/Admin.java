package com.users;

import com.branch.BranchName;
import com.branch.Branch;
import com.cache.BranchCache;
import com.cache.EmployeeCache;
import com.payment.PaymentMethod;

/**
 * Represents an Admin, a type of Employee with additional privileges.
 * @see Employee
 */
public class Admin extends Employee{

    /** Cache for storing employee information. */
    protected static EmployeeCache employeeCache = EmployeeCache.getInstance();

    /**
     * Constructs a new Admin object with the specified parameters.
     * @param name The name of the admin.
     * @param loginID The login ID of the admin.
     * @param role The role of the admin.
     * @param gender The gender of the admin.
     * @param age The age of the admin.
     * @param branch The branch the admin belongs to.
     * @param password The password of the admin.
     */
    public Admin(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }

    /**
     * Adds a new staff account based on the specified parameters.
     * @param name The name of the staff member.
     * @param loginID The login ID of the staff member.
     * @param role The role of the staff member.
     * @param gender The gender of the staff member.
     * @param age The age of the staff member.
     * @param branch The branch the staff member belongs to.
     * @param password The password of the staff member.
     */
    public void addStaffAccounts(String name,String loginID,StaffRole role,Gender gender,int age,Branch branch,String password){
        switch (role) {
            case S:
                employeeCache.addItem(name, new Staff(name, loginID, role, gender, age, branch, password));      
                break;
            case M:
                employeeCache.addItem(name, new Manager(name, loginID, role, gender, age, branch, password));      
        
            default:
                break;
        }
    }

    /**
     * Removes a staff account based on the login ID.
     * @param loginID The login ID of the staff member to remove.
     */
    public void removeStaffAccount(String loginID){
        employeeCache.removeItem(loginID);
    }

    /**
     * Edits the name of a staff member.
     * @param emp The staff member to edit.
     * @param name The new name for the staff member.
     */
    public void editStaffName(Employee emp,String name){
        emp.setName(name);
    }

    /**
     * Edits the login ID of a staff member.
     * @param emp The staff member to edit.
     * @param loginID The new login ID for the staff member.
     */
    public void editStaffLoginID(Employee emp, String loginID){
        emp.setLoginID(loginID);
    }

    /**
     * Edits the age of a staff member.
     * @param emp The staff member to edit.
     * @param age The new age for the staff member.
     */
    public void editStaffAge(Employee emp, int age){
        emp.setAge(age);
    }

    /**
     * Edits the gender of a staff member.
     * @param emp The staff member to edit.
     * @param gender The new gender for the staff member.
     */
    public void editStaffGender(Employee emp, Gender gender){
        emp.setGender(gender);
    }

    /**
     * Edits the password of a staff member.
     * @param emp The staff member to edit.
     * @param password The new password for the staff member.
     */
    public void editStaffPassword(Employee emp,String password){
        emp.setPassword(password);
    }

    /**
     * Promotes a staff member to a manager role.
     * @param loginID The login ID of the staff member to promote.
     */
    public void promoteStaff(String loginID){
        Employee staff = employeeCache.getItem(loginID);
        employeeCache.removeItem(loginID);
        employeeCache.addItem(loginID,new Manager(staff.getName(), staff.getLoginID(), StaffRole.M, staff.getGender(), staff.getAge(), staff.getBranch(), staff.getPassword()));
        System.out.println("You have successfully promoted " + staff.getName() + " to Manager.");
    }

    /**
     * Transfers a staff member to a different branch.
     * @param emp The staff member to transfer.
     * @param branch The branch to transfer the staff member to.
     */
    public void transferStaff(Employee emp, Branch branch){
        emp.setBranch(branch);
        System.out.println(emp.getName() + " has been transferred successfully");
    }

    /**
     * Edits the payment method settings.
     * @param method The payment method to add or remove.
     * @param bool If true, adds the payment method. If false, removes it.
     */
    public void editPayments(String method,Boolean bool){
        if (bool) {
            PaymentMethod.addValue(method);
        }
        else{
            PaymentMethod.removeValue(method);
        }
    }

    /**
     * Removes a branch from the branch cache.
     * @param branchName The name of the branch to remove.
     * @param branchCache The cache of branches.
     */
    public void removeBranch(BranchName branchName, BranchCache branchCache){
        branchCache.removeItem(branchName);
    }

}
