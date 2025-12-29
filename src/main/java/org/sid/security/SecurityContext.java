package org.sid.security;

import java.util.HashMap;
import java.util.Map;

public class SecurityContext {

    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();
    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("admin", new User("admin", "admin123", "ADMIN", "AGENT"));
        users.put("agent1", new User("agent1", "agent123", "AGENT"));
        users.put("user1", new User("user1", "user123", "USER"));
    }

    public static boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser.set(user);
            return true;
        }
        return false;
    }

    public static void logout() {
        currentUser.remove();
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static boolean isAuthenticated() {
        return currentUser.get() != null;
    }

    public static boolean hasRole(String role) {
        User user = currentUser.get();
        return user != null && user.hasRole(role);
    }
}
