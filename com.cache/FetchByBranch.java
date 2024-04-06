package com.cache;

import java.util.List;
import java.util.stream.Collectors;

import com.branch.BranchName;
import com.cache.AppCache;

public class FetchByBranch<T> implements Retriever<T> {
    private AppCache<T> cache;
    private BranchName branchName;

    public FetchByBranch(AppCache<T> cache, BranchName branchName) {
        this.cache = cache;
        this.branchName = branchName;
    }

    @Override
    public List<T> retrieve() {
        // Implementation logic to filter items by branchName
        // This assumes each T item can provide branch information via a common method
        return cache.getFilteredItems().stream()
                    .filter(item -> item.getBranch().getBranchName().equals(branchName))
                    .collect(Collectors.toList());
    }
}
