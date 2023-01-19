package com.krillinator.springSecurityLektion.user.authorities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static com.krillinator.springSecurityLektion.user.authorities.UserPermissions.*;

public enum UserRoles {

    USER(List.of(USER_READ)),
    ADMIN(List.of(ADMIN_READ, ADMIN_WRITE));

    // Variable
    private final List<UserPermissions> permissionsList;

    // Constructor
    UserRoles(List<UserPermissions> permissions) {
        this.permissionsList = permissions;
    }

    // Getter
    public List<UserPermissions> getPermissions() {
        return permissionsList;
    }

    // Create list: [ROLE & PERMISSIONS]
    public List<SimpleGrantedAuthority> getGrantedAuthorities() {

        // Loop
        List<SimpleGrantedAuthority> permissionsList = new ArrayList<>(getPermissions().stream().map(
                index -> new SimpleGrantedAuthority(index.getUserPermission())
        ).toList());

        // Add Role      (example ROLE_ADMIN)
        permissionsList.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissionsList;
    }
}






