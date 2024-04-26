package com.cache;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.branch.Branch;
import com.branch.BranchName;

/**
* Singleton class to manage caching of Branch objects.
* Ensures that each branch is uniquely identified by a BranchName.
* @see AppCache
*/
public class BranchCache extends AppCache<BranchName, Branch, Branch>{

    /** The singleton instance of BranchCache. */
    private static BranchCache instance;

    /**
    * Constructs an BranchCache object.
    * Private constructor to prevent direct instantiation.
    */
    private BranchCache() {}

    
    /**
    * Retrieves the singleton instance of BranchCache.
    *
    * @return The singleton instance of BranchCache.
    */
    public static synchronized BranchCache getInstance() {
        if (instance == null) {
            instance = new BranchCache();
        }
        return instance;
    }

    /**
    * Adds an branch to the cache with the specified BranchName
    * If an employee with the same BranchName already exists in the cache, an error message is printed.
    *
    * @param name The name of the branch.
    * @param branch The branch object to be added to the cache.
    */
    @Override
    public void addItem(BranchName name, Branch branch) {
        if (cacheItems.containsKey(name)) {
            System.out.println("Branch "+ name + " already exists.");
        } else {
            cacheItems.put(name, branch);
            System.out.println("Branch " + name + " successfully added.");
        }
    }

    /**
    * Removes a branch from the cache using the specified BranchName and optional filters
    * If the branch is not found in the cache, an error message is printed.
    *
    * @param name The name of the branch to remove.
    * @param filters Optional filters to apply when removing the branch.
    */
    @SafeVarargs
    @Override
    public final void removeItem(BranchName name, Optional<Predicate<Object>>... filters) {
        if (cacheItems.containsKey(name)) {
            cacheItems.remove(name);
            System.out.println(name + " is removed.");
        } else {
            System.out.println("Unable to remove branch. (Branch not found)");
        }
    }

    /**
    * Retrieves a branch from the cache using the specified Branch name and optional filters.
    * If the branch is not found in the cache, null is returned.
    *
    * @param name The name of the branch to retrieve.
    * @param filters Optional filters to apply when retrieving the branch. Not implemented.
    * @return The retrieved branch object, or null if not found.
    */
    @SafeVarargs
    @Override
    public final Branch getItem(BranchName name, Optional<Predicate<Object>>... filters) {
        if (cacheItems.containsKey(name)) {
            return cacheItems.get(name);
        } else {
            // System.out.println("Unable to retrieve branch. (Branch not found)");
            return null;
        }
    }

    /**
    * Prints all branches in the cache using the provided function for formatting.
    *
    * @param function The function to format the Branches for printing.
    */
    @Override    
    public void printAllItems(Function<Branch, ?> function) {
        cacheItems.values().stream()
                        .map(function)
                        .forEach(System.out::println);
    }
    
}
