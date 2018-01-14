package com.indiraactive.stockupdaterserver.service;

import com.indiraactive.stockupdaterserver.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public boolean validateUser(User user) {
        return true;
    }
}
