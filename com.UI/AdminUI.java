package com.UI;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.users.Admin;
import com.users.Employee;
import com.cache.EmployeeCache;
import com.cache.MenuCache;
import com.cache.OrderCache;
import com.cache.BranchCache;
import com.users.StaffRole;
import com.users.Staff;
import com.users.Gender;
import com.branch.Branch;
import com.payment.PaymentMethod;

public class AdminUI {

    protected static EmployeeCache employeeCache = EmployeeCache.getInstance();
    protected static MenuCache menuCache = MenuCache.getInstance();
    protected static BranchCache branchCache = BranchCache.getInstance();
    protected static OrderCache orderCache = OrderCache.getInstance();

    
    private Admin admin;

    public AdminUI(Employee employee) {
        this.admin = (Admin) employee;
    }

    public void displayMenu(Scanner scanner){
        System.out.println("[=+=] Admin Interface [=+=]");
        System.out.println("(1) Add Staff Accounts");
        System.out.println("(2) Remove Staff Accounts");
        System.out.println("(3) Edit Staff Accounts");
        System.out.println("(4) Display staff list");
        System.out.println("(5) Assign managers to branch");
        System.out.println("(6) Promote staff");
        System.out.println("(7) Transfer a staff");
        System.out.println("(8) Transfer a manager");
        System.out.println("(9) Add/Remove payment method");
        System.out.println("(10) Open/Close branch");
        System.out.println("(11) Logout");

        int choice = 0;
        String loginID;
        Employee staff;
            do {
                try {
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            //check if manager and staff quota are h
t
                            Branch branch = enterBranch(scanner);
                            if(branch.quotaFull(branch, employeeCache)){
                                break;
                            }
                            StaffRole role = enterRole(scanner);
                            if (role == StaffRole.M && branch.managerFull(branch, employeeCache)){
                             ///   break;
                            }
                            String name = enterName(scanner);
                            loginID = enterLoginID(scanner);
                            Gender gender = enterGender(scanner);
                            int age = enterAge(scanner);
                            String password = enterPassword(scanner);
                            admin.addStaffAccounts(name,loginID,role,gender,age,branch,password);
                            break;
                        case 2:
                            loginID = enterLoginID(scanner);
                            admin.removeStaffAccount(loginID);
                            break;
                        case 3:
                            loginID = enterLoginID(scanner);
                            staff = employeeCache.getItem(loginID);
                            editAccount(scanner,staff);
                            break;
                        case 4:
                            
                            admin.displayStaffList(scanner);
                            break;
                        case 5:
                            loginID = enterLoginID(scanner);
                            staff = employeeCache.getItem(loginID);
                            if (staff.getRole() != StaffRole.M) {
                                System.out.println("Error. Not a manager.");
                                break;
                            }
                            branch = enterBranch(scanner);
                            admin.transferManager(staff,branch);
                            break;
                        case 6:
                            //check if the promotion causes manager quota to be hit
                            if (branch.getStaffCount() - 1 <= 4 && branch.getManagerCount() == 1) {
                                System.out.println("error: manager quota has been reached");
                                break;
                            }
                            else if (branch.getStaffCount() - 1 <=8 && branch.getManagerCount() == 2){
                                System.out.println("error: manager quota has been reached");
                                break;
                            }
                            else if (branch.getStaffCount() - 1 <= 15 && branch.getManagerCount() == 3){
                                System.out.println("error: manager quota has been reached");
                                break;
                            }
                            loginID = enterLoginID(scanner);
                            admin.promoteStaff(loginID);
                            break;
                        case 7:
                            loginID = enterLoginID(scanner);
                            staff = employeeCache.getItem(loginID);
                            if (staff.getRole() != StaffRole.S) {
                                System.out.println("Invalid Employee type");
                                break;
                            }
                            branch = enterBranch(scanner);
                            //check if staff quota has been hit
                            if(branch.quotaFull(branch, employeeCache)){
                                break;
                            }
                            admin.transferStaff(staff,branch);
                            break;
                        case 8:
                            loginID = enterLoginID(scanner);
                            staff = employeeCache.getItem(loginID);
                            if (staff.getRole() != StaffRole.M) {
                                System.out.println("Invalid Employee type");
                                break;
                            }
                            branch = enterBranch(scanner);
                            //check whether manager quota has been hit
                            if(branch.managerFull(branch, employeeCache)){
                                break;
                            }
                            admin.transferManager(staff,branch);
                            break;
                        case 9:
                            editpayments(scanner);
                            break;
                        case 10:
                            branch = enterBranch(scanner);
                            System.out.println("Enter Branch status: 1 for Open, 0 for Close");
                            int status = scanner.nextInt();
                            if (status == 1){
                                admin.changeBranchStatus(branch, true);
                            }
                            else if (status == 2){
                                admin.changeBranchStatus(branch, false);
                            }
                            else{
                                System.out.println("wrong input.");
                            }
                            
                            break;
                        case 11:
                            System.out.println("Logging Out...");
                            break;
                        default:
                            System.out.println("Invalid choice. Enter options between (1) to (11).");
                            break;
                    }
                } catch(InputMismatchException e) {
                    System.err.println("Error Input! Enter only numbers!");
                }
            } while (choice < 0 || choice > 11);
    }

    private String enterName(Scanner scanner) {
        System.out.println("Enter staff name: ");
        try {
            String name = scanner.nextLine();
            return name;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input. Try again. ");
            return scanner.nextLine();
            }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again. ");
            return scanner.nextLine();
            }
    }

    private String enterLoginID(Scanner scanner) {
        System.out.println("Enter Login ID: ");
        try {
            String name = scanner.nextLine();
            return name;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input. Try again. ");
            return scanner.nextLine();
            }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again. ");
            return scanner.nextLine();
            }
        }

    private StaffRole enterRole(Scanner scanner){
        System.out.println("Enter Staff Role [Staff(S) / Manager (M)]: ");
        try {
            char str_role = Character.toUpperCase(scanner.nextLine().charAt(0));
            if (str_role == 'S') {
                return StaffRole.S;
            }
            else if(str_role == 'M'){
                return StaffRole.M;
            }
            else{
                System.out.println("Invalid input");
                return enterRole(scanner);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input");
            return enterRole(scanner);
            }
        catch (InputMismatchException e) {
            System.out.println("Invalid input");
            return enterRole(scanner);
            }
        }

    private Gender enterGender(Scanner scanner){
        System.out.print("Enter Staff Gender (M/F): ");
        try {
            char str_gender = Character.toUpperCase(scanner.nextLine().charAt(0));
            if (str_gender == 'M') {
                return Gender.M;
            }
            else if(str_gender == 'F'){
                return Gender.F;
            }
            else{
                System.out.println("Invalid input");
                return enterGender(scanner);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input");
            return enterGender(scanner);
            }
        catch (InputMismatchException e) {
            System.out.println("Invalid input");
            return enterGender(scanner);
            }
        } 

    private int enterAge(Scanner scanner){
        System.out.print("Enter Staff Age: ");
        String input = scanner.nextLine();
        try{
            int age = Integer.parseInt(input);
            return age;
        }
        catch(Exception e){
            return enterAge(scanner);
        }
    }

    private Branch enterBranch(Scanner scanner){
        System.out.println("Available branches:");
        branchCache.printAllItems();
        System.out.print("\nAssign branch: ");
        scanner.
        



    }

    private String enterPassword(Scanner scanner){
        try{
            System.out.println("Enter staff password: ");
            return scanner.nextLine();
        }catch(Exception e){
            System.out.println("Invalid input");
            return enterPassword(scanner);
        }
    }

    private void editAccount(Scanner scanner, Employee emp){
        System.out.println("(1)Edit staff name");
        System.out.println("(2)Edit staff loginID");
        System.out.println("(3)Edit staff gender");
        System.out.println("(4)Edit staff age");
        System.out.println("(5)Edit staff password");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                admin.editStaffName(emp,enterName(scanner));
                break;
            case 2:
                admin.editStaffLoginID(emp,enterLoginID(scanner));
                break;
            case 3:
                admin.editStaffGender(emp,enterGender(scanner));
            case 4:
                admin.editStaffAge(emp,enterAge(scanner));
            case 5:
                admin.editStaffPassword(emp,enterPassword(scanner));
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    private void editpayments(Scanner scanner){
        System.out.println("(1) to add payment method");
        System.out.println("(2) to remove payment method");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter new payment method: ");
                admin.editpayments(scanner.nextLine(),true);
                break;
            case 2:
                System.out.print("Enter payment method to remove: ");
                admin.editpayments(scanner.nextLine(),false);
            default:
                System.out.println("Invalid input");
                break;
        }

    }
 
    private void filterStaffList(Scanner scanner){
        System.out.println("(1) for no filter");
        System.out.println("(2) to filter by branch");
        System.out.println("(3) to filter by role");
        System.out.println("(4) to filter by gender"); 
        System.out.println("(5) to filter by age"); 
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                admin.
                break;
        
            default:
                break;
        }
    }
}
