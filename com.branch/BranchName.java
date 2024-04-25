package com.branch;

import java.util.function.Predicate;

import com.menu.MenuItem;
import com.users.Employee;

public enum BranchName implements Predicate<Object> {
    NTU,
    JP, 
    JE,
    NA;

    @Override
    public boolean test(Object o) {
        if (o instanceof Branch) {
            return ((Branch) o).getBranchName().equals(this);
        }
        else if (o instanceof MenuItem) {
            return ((MenuItem) o).getBranch().getBranchName().equals(this);
        }
        else if (o instanceof Employee) {
            return ((Employee) o).getBranch().getBranchName().equals(this);
        }
        else if (o instanceof BranchName) {
            return ((BranchName) o).equals(this);
        }
        return false;
    }

}
