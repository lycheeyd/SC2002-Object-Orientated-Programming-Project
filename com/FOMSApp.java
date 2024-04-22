package com;

import com.datautils.loadBranch;
import com.datautils.loadData;
import com.datautils.loadMenu;
import com.order.AutoCancelService;
import com.datautils.loadEmployee;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.UI.StartupUI;

public class FOMSApp {
    // initialisation and populate cache storages from files
    private static void Startup() {
        System.out.println("[=+=] Booting up system... [=+=]\n");
        List<loadData> loaders = Arrays.asList(new loadBranch(), new loadMenu(), new loadEmployee());
        loaders.forEach(loadData::loadFile);
        System.out.println("\n[=+=] Welcome to FOMSapp [=+=]\n");
    }

    public static void Shutdown() {
        AutoCancelService.getInstance().shutdown();
//        List<saveData> savers = Arrays.asList();
//        savers.forEach(saveData::saveFile);

        System.exit(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Startup();
        new StartupUI().displayMenu(scanner);
    }
}
