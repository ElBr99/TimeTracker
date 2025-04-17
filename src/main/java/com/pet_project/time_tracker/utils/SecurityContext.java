package com.pet_project.time_tracker.utils;

import com.pet_project.time_tracker.model.User;
import lombok.Getter;
import lombok.experimental.UtilityClass;


public class SecurityContext {

    private static User currentUser;

    public static void setCurrentUser(User currentUser) {
        SecurityContext.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
