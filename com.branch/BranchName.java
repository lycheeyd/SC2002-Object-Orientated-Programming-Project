package com.branch;

import java.util.function.Predicate;

import com.menu.MenuItem;

public enum BranchName implements Predicate<Object> {
    NTU,
    JP, 
    JE;

    @Override
    public boolean test(Object o) {
        if (o instanceof Branch) {
            return ((Branch) o).getBranchName().equals(this);
        }
        else if (o instanceof MenuItem) {
            return ((MenuItem) o).getBranch().getBranchName().equals(this);
        }
        return false;
    }

}
