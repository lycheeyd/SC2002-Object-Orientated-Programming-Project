package com.UI;

import com.cache.EmployeeCache;

public class Authenticator {
    
    public static boolean Authenticate(String loginID, String password) {
        if (EmployeeCache.getInstance().getItem(loginID).getPassword() == password) {
            return true;
        }
        return false;
    }
    
}
