package com.indiraactive.stockupdaterserver.service;

import com.indiraactive.stockupdaterserver.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator { //TODO: Make this class more robust when adding security to app
    public boolean validateUser(User user) {
        return true;
    }
}
