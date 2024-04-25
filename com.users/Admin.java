package com.users;

import com.branch.BranchName;
import com.branch.Branch;
import com.cache.BranchCache;
import com.cache.EmployeeCache;
import com.payment.PaymentMethod;

public class Admin extends Employee{

    protected static EmployeeCache employeeCache = EmployeeCache.getInstance();

    public Admin(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }

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

    public void removeStaffAccount(String loginID){
        employeeCache.removeItem(loginID);
    }

    public void editStaffName(Employee emp,String name){
        emp.setName(name);
    }

    public void editStaffLoginID(Employee emp, String loginID){
        emp.setLoginID(loginID);
    }

    public void editStaffAge(Employee emp, int age){
        emp.setAge(age);
    }

    public void editStaffGender(Employee emp, Gender gender){
        emp.setGender(gender);
    }

    public void editStaffPassword(Employee emp,String password){
        emp.setPassword(password);
    }

    /*public void displayStaffList(Scanner scanner){
        employeeCache.printAllItems();
    }*/

    /*public void assignManagers(){

    }*/

    public void promoteStaff(String loginID){
        Employee staff = employeeCache.getItem(loginID);
        employeeCache.removeItem(loginID);
        employeeCache.addItem(loginID,new Manager(staff.getName(), staff.getLoginID(), staff.getRole(), staff.getGender(), staff.getAge(), staff.getBranch(), staff.getPassword()));
    }


    //transfer staff among branches
    public void transferStaff(Employee emp, Branch branch){
        emp.setBranch(branch);
    }

    public void transferManager(Employee emp, Branch branch){
        emp.setBranch(branch);
    }

    public void editpayments(String method,Boolean bool){
        if (bool) {
            PaymentMethod.addValue(method);
        }
        else{
            PaymentMethod.removeValue(method);
        }

    }

    public void changeBranchStatus(Branch branch, Boolean bool){
        //branch.setBranchStatus(bool);
    }

    public void removeBranch(BranchName branchName, BranchCache branchCache){
        branchCache.removeItem(branchName);
    }


}
