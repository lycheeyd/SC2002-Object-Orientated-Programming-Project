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

public class FOMSApp {
    // initialisation and populate cache storages from files
    private static void Startup() {
        System.out.println("[=+=] Booting up system... [=+=]\n");
        List<LoadData> loaders = Arrays.asList(new LoadBranch(), new LoadMenu(), new LoadEmployee());
        loaders.forEach(LoadData::loadFile);
        System.out.println("\n[=+=] Welcome to FOMSapp [=+=]\n");
    }

    public static void Shutdown() {
        System.out.println("[=+=] Terminating system processes... [=+=]\n");
        AutoCancelService.getInstance().shutdown();

        System.out.println("[=+=] Saving files... [=+=]\n");
        List<SaveData> savers = Arrays.asList(new SaveBranch(), new SaveMenu(), new SaveEmployee());
        savers.forEach(SaveData::saveToFile);

        System.out.println("\n[=+=] Shutting down... [=+=]");
        System.exit(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Startup();
        new StartupUI().displayMenu(scanner);
    }
}
