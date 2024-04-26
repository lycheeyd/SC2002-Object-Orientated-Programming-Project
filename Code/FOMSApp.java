package com;

import com.datautils.LoadBranch;
import com.datautils.LoadData;
import com.datautils.LoadMenu;
import com.datautils.LoadEmployee;
import com.datautils.SaveBranch;
import com.datautils.SaveData;
import com.datautils.SaveEmployee;
import com.datautils.SaveMenu;
import com.order.AutoCancelService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.UI.StartupUI;

/**
 * The main class of the FOMS (FastFood Order Management System) application.
 * This class handles the startup, shutdown, and main execution flow of the application.
 */
public class FOMSApp {

    /**
    * Default constructor.
    */
    public FOMSApp() {};

    /**
    * initialisation and populate cache storages from files.
    */
    private static void Startup() {
        System.out.println("[=+=] Booting up system... [=+=]\n");
        List<LoadData> loaders = Arrays.asList(new LoadBranch(), new LoadMenu(), new LoadEmployee());
        loaders.forEach(LoadData::loadFile);
        System.out.println("\n[=+=] Welcome to FOMSapp [=+=]\n");
    }

    /**
    * Serialisation of system data and shutdown the application.
    */
    public static void Shutdown() {
        System.out.println("[=+=] Terminating system processes... [=+=]\n");
        AutoCancelService.getInstance().shutdown();

        System.out.println("[=+=] Saving files... [=+=]\n");
        List<SaveData> savers = Arrays.asList(new SaveBranch(), new SaveMenu(), new SaveEmployee());
        savers.forEach(SaveData::saveToFile);

        System.out.println("\n[=+=] Shutting down... [=+=]");
        System.exit(0);
    }

    
    /** 
     * Main method of the FOMSApp
     * @param args main method
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Startup();
        new StartupUI().displayMenu(scanner);
    }
}
