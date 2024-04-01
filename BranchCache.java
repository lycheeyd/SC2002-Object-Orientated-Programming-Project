package com.cache;

import java.util.HashMap;
import java.util.Map;

import com.branch.Branch;
import com.branch.BranchName;

public class BranchCache {

    private static BranchCache instance;
    private Map<BranchName, Branch> branches = new HashMap<>();

    private BranchCache() {}

    public static synchronized BranchCache getInstance() {
        if (instance == null) {
            instance = new BranchCache();
        }
        return instance;
    }

    public void addBranch(BranchName key, Branch branch) {
        branches.put(key, branch);
    }

    public Branch getBranch(BranchName key) {
        return branches.get(key);
    }

    public void printAllItems() {
        branches.values().forEach(System.out::println);
    }

}
