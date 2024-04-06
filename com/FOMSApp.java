package com;

import com.cache.AppCache;
import com.cache.BranchCache;
import com.cache.EmployeeCache;
import com.cache.MenuCache;
import com.datautils.loadBranch;
import com.datautils.loadData;
import com.datautils.loadMenu;
import com.datautils.loadEmployee;

public class FOMSApp {
    // initialisation and populate cache storages from files
    public static void startUp() {
        // initialise objects
        loadData branchLoader = new loadBranch();
        loadData menuLoader = new loadMenu();
        loadData employeeLoader = new loadEmployee();

        // populate cache storages from files
        branchLoader.loadFile();
        menuLoader.loadFile();
        employeeLoader.loadFile();



    }
    public static void main(String[] args) {
        // initialise
        startUp();
/* 
        // initialise cache references
        AppCache branchCache = BranchCache.getInstance();
        AppCache menuCache = MenuCache.getInstance();
        AppCache employeeCache = EmployeeCache.getInstance();
*/
        BranchCache.getInstance().printAllItems();
        MenuCache.getInstance().printAllItems();
        EmployeeCache.getInstance().printAllItems();




    
    }
}
