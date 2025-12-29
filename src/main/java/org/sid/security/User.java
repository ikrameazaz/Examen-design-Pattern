package org.sid.security;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final String username;
    private final String password;
    private final Set<String> roles;

    public User(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
        if (roles != null) {
            Collections.addAll(this.roles, roles);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public boolean hasAnyRole(String... requiredRoles) {
        for (String role : requiredRoles) {
            if (roles.contains(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', roles=" + roles + "}";
    }
}
