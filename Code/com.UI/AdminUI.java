package com.UI;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

import com.users.Admin;
import com.users.Employee;
import com.cache.EmployeeCache;
import com.cache.MenuCache;
import com.cache.OrderCache;
import com.cache.BranchCache;
import com.users.StaffRole;
import com.users.Gender;
import com.branch.Branch;
import com.branch.BranchName;

public class AdminUI {

    protected static EmployeeCache employeeCache = EmployeeCache.getInstance();
    protected static MenuCache menuCache = MenuCache.getInstance();
    protected static BranchCache branchCache = BranchCache.getInstance();
    protected static OrderCache orderCache = OrderCache.getInstance();

    private Admin admin;
    int choice = 0;

    public AdminUI(Employee employee) {
        this.admin = (Admin) employee;
    }

    public void displayMenu(Scanner scanner){

        do {
        
            System.out.println("\n[=+=] Admin Interface [=+=]");
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
            System.out.print("\nWaiting for user input: ");

            String loginID;
            Employee staff;
            Branch branch;
                
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        //check if manager and staff quota are hit
                        branch = enterBranch(scanner);
                        if(branch.quotaFull(branch, employeeCache)){
                            break;
                        }
                        StaffRole role = enterRole(scanner);
                        if (role == StaffRole.M && branch.managerFull(branch, employeeCache)){
                           break;
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
                        if (staff == null) {
                            System.out.println("LoginID does not exist");
                            break;
                        }
                        editAccount(scanner,staff);
                        System.out.println("Account edited successfully!");
                        break;
                    case 4:
                        filterStaffList(scanner);
                        break;
                    case 5:
                        loginID = enterLoginID(scanner);
                        staff = employeeCache.getItem(loginID);
                        if (staff == null) {
                            System.out.println("LoginID does not exist");
                            break;
                        }
                        if (staff.getRole() != StaffRole.M) {
                            System.out.println("Error. Not a manager.");
                            break;
                        }
                        branch = enterBranch(scanner);
                        if (branch == null) {
                            break;
                        }
                        admin.transferStaff(staff, branch);
                        break;
                    case 6:
                        //check if the promotion causes manager quota to be hit
                        loginID = enterLoginID(scanner);
                        staff = employeeCache.getItem(loginID);
                        branch = staff.getBranch();
                        if (staff == null || branch == null) {
                            System.out.println("Invalid LoginID or Branch.");
                            break;
                        }
                        if (branch.getStaffCount(employeeCache) - 1 <= 4 && branch.getManagerCount(employeeCache) == 1) {
                            System.out.println("error: manager quota has been reached");
                            break;
                        }
                        else if (branch.getStaffCount(employeeCache) - 1 <=8 && branch.getManagerCount(employeeCache) == 2){
                            System.out.println("error: manager quota has been reached");
                            break;
                        }
                        else if (branch.getStaffCount(employeeCache) - 1 <= 15 && branch.getManagerCount(employeeCache) == 3){
                            System.out.println("error: manager quota has been reached");
                            break;
                        }
                        admin.promoteStaff(loginID);
                        break;
                    case 7:
                        loginID = enterLoginID(scanner);
                        staff = employeeCache.getItem(loginID);
                        if (staff == null) {
                            System.out.println("LoginID does not exist");
                            break;
                        }
                        if (staff.getRole() != StaffRole.S) {
                            System.out.println("Invalid Employee type");
                            break;
                        }
                        branch = enterBranch(scanner);
                        if (branch == null) {
                            break;
                        }
                        if (branch.getBranchName().equals(staff.getBranch().getBranchName())){
                            System.out.println("Unable to transfer to same branch.");
                            break;
                        }
                        //check if staff quota has been hit
                        if(branch.quotaFull(branch, employeeCache)){
                            break;
                        }
                        admin.transferStaff(staff,branch);
                        break;
                    case 8:
                        loginID = enterLoginID(scanner);
                        staff = employeeCache.getItem(loginID);
                        if (staff == null) {
                            System.out.println("LoginID does not exist.");
                            break;
                        }

                        if (staff.getRole() != StaffRole.M) {
                            System.out.println("Invalid Employee type");
                            break;
                        }
                        branch = enterBranch(scanner);
                        if (branch == null) {
                            break;
                        }
                        if (branch.getBranchName().equals(staff.getBranch().getBranchName())){
                            System.out.println("Unable to transfer to same branch.");
                            break;
                        }
                        //check whether manager quota has been hit
                        if(branch.managerFull(branch, employeeCache)){
                            break;
                        }
                        admin.transferStaff(staff, branch);
                        break;
                    case 9:
                        editPayments(scanner);
                        break;
                    case 10:   
                        openCloseBranch(scanner);
                        break;
                    case 11:
                        System.out.println("Logging Out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Enter options between (1) to (11).");
                        break;
                }
            } catch(InputMismatchException e) {
                System.err.println("Error Input! Enter only numbers!");
                scanner.nextLine();
            }
        } while (true);
    }

    private String enterName(Scanner scanner) {
        System.out.print("Enter staff name: ");
        try {
            String name = scanner.next();
            return name;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input. Try again. ");
            return scanner.next();
            }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again. ");
            return scanner.next();
            }
    }

    private String enterLoginID(Scanner scanner) {
        System.out.print("Enter Login ID: ");
        try {
            String name = scanner.next();
            return name;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input. Try again. ");
            return scanner.next();
            }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again. ");
            return scanner.next();
            }
        }

    private StaffRole enterRole(Scanner scanner){
        System.out.print("Enter Staff Role [Staff(S) / Manager (M)]: ");
        try {
            char str_role = Character.toUpperCase(scanner.next().charAt(0));
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
            char str_gender = Character.toUpperCase(scanner.next().charAt(0));
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
        String input = scanner.next();
        try{
            int age = Integer.parseInt(input);
            return age;
        }
        catch(InputMismatchException e){
            System.out.println("Error input! Enter only numbers.");
            return enterAge(scanner);
        }
    }

    private BranchName enterBranchName(Scanner scanner) {
        System.out.println("\nAvailable branches: ");
        branchCache.printAllItems(Branch::getBranchName);

        System.out.print("\nSelect branch: ");
        try {
            BranchName branchName = BranchName.use(scanner.next().toUpperCase());
            return branchName;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid branch entered. Try again.");
            return enterBranchName(scanner);            
        }
    }

    private Branch enterBranch(Scanner scanner){
        BranchName branchName = enterBranchName(scanner);
        return branchCache.getItem(branchName);
    }

    private String enterPassword(Scanner scanner){
        try{
            System.out.print("Enter staff password: ");
            return scanner.next();
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
        try {
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
                    break;
                case 4:
                    admin.editStaffAge(emp,enterAge(scanner));
                    break;
                case 5:
                    admin.editStaffPassword(emp,enterPassword(scanner));
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } catch(InputMismatchException e) {
            System.err.println("Error Input! Enter only numbers!");
            scanner.nextLine();
        }
    }

    private void editPayments(Scanner scanner){
        System.out.println("(1) to add payment method");
        System.out.println("(2) to remove payment method");
        System.out.print("\nWaiting for user input: ");
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter new payment method: ");
                    admin.editPayments(scanner.next(),true);
                    break;
                case 2:
                    System.out.println("");
                    System.out.print("Enter payment method to remove: ");
                    admin.editPayments(scanner.next(),false);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } catch(InputMismatchException e) {
            System.err.println("Error Input! Enter only numbers!");
            scanner.nextLine();
        }
    }
 
    private void filterStaffList(Scanner scanner){
        System.out.println("(1) Display all staffs");
        System.out.println("(2) to filter by branch");
        System.out.println("(3) to filter by role");
        System.out.println("(4) to filter by gender"); 
        System.out.println("(5) to filter by minimum age"); 
        System.out.print("\nWaiting for user input: ");
        
        try {
            int choice = scanner.nextInt();
            switch (choice) {
            case 1:
                employeeCache.printAllItems(Employee::toString);
                break;

            case 2:
                BranchName bname = enterBranchName(scanner);
                employeeCache.printFilteredItems(bname);
                break;

            case 3:
                StaffRole role = enterRole(scanner);
                employeeCache.printFilteredItems(role);
                break;
            
            case 4:
                Gender gender = enterGender(scanner);
                employeeCache.printFilteredItems(gender);
                break;

            case 5:
                int age = enterAge(scanner);
                Predicate<Object> ageFilter = o -> ((Employee) o).getAge() >= age;
                employeeCache.printFilteredItems(ageFilter);
                break;
            default:
                break;
        }
        } catch(InputMismatchException e) {
                System.err.println("Error Input! Enter only numbers!");
                scanner.nextLine();
        }
    }

    private void openCloseBranch(Scanner scanner) {
        System.out.println("(0) To close a branch");
        System.out.println("(1) To open a branch");
        System.out.print("\nWaiting for user input: ");
        
        try {
            int choice = scanner.nextInt();
            switch (choice) {
            case 0:
                removeBranch(scanner);
                break;
            case 1:
                openBranch(scanner);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
        } catch(InputMismatchException e) {
                System.err.println("Error Input! Enter only numbers!");
                scanner.nextLine();
        }
    }
    

    private void removeBranch(Scanner scanner) {
        Branch branch = enterBranch(scanner);
        admin.removeBranch(branch.getBranchName(),branchCache);
    }

    private void openBranch(Scanner scanner) {
        System.out.print("\nEnter new branch name: ");
        String branchname = scanner.next().toUpperCase();
        BranchName.addName(branchname);
        BranchName branchName = BranchName.use(branchname);
        System.out.print("Enter new branch location:");
        String location = scanner.next();
        System.out.print("Enter new branch staff quota:");
        int quota = scanner.nextInt();
        branchCache.addItem(branchName, new Branch(branchName,location,quota));
    }
}