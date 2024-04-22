package com.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.branch.Branch;
import com.branch.BranchName;

public class BranchCache extends AppCache<BranchName, Branch, Branch>{

    private static BranchCache instance;
//    protected Map<BranchName, Branch> cacheItems = new HashMap<>();

    private BranchCache() {}

    public static synchronized BranchCache getInstance() {
        if (instance == null) {
            instance = new BranchCache();
        }
        return instance;
    }

    @Override
    public void addItem(BranchName name, Branch branch) {
        if (cacheItems.containsKey(name)) {
            System.out.println("Branch "+ name + " already exists.");
        } else {
            cacheItems.put(name, branch);
            System.out.println("Branch " + name + " successfully added.");
        }
    }

    @SafeVarargs
    @Override
    public final void removeItem(BranchName name, Optional<Predicate<Branch>>... filters) {
        if (cacheItems.containsKey(name)) {
            cacheItems.remove(name);
            System.out.println(name + "is removed.");
        } else {
            System.out.println("Unable to remove branch. (Branch not found)");
        }
    }

    @SafeVarargs
    @Override
    public final Branch getItem(BranchName name, Optional<Predicate<Branch>>... filters) {
        if (cacheItems.containsKey(name)) {
            return cacheItems.get(name);
        } else {
            // System.out.println("Unable to retrieve branch. (Branch not found)");
            return null;
        }
    }

    @Override    
    public void printAllItems(Function<Branch, ?> function) {
        cacheItems.values().stream()
                        .map(function)
                        .forEach(System.out::println);
    }


}
