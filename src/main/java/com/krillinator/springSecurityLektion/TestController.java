package com.krillinator.springSecurityLektion;

import com.krillinator.springSecurityLektion.configurations.AppPasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REMINDER:
 *  Delete this later
 * */

@RestController
@RequestMapping("/rest")
public class TestController {

    private final AppPasswordConfig bcrypt;

    @Autowired
    public TestController(AppPasswordConfig bcrypt) {
        this.bcrypt = bcrypt;
    }

    @GetMapping("/encode")
    public String testEncoding() {

        bcrypt.bCryptPasswordEncoder().matches("", "");

        return bcrypt.bCryptPasswordEncoder().encode("password");
    }




    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAdminPermission() {

        return "Only admins can enter";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String testUserPermission() {

        return "Only user can enter";
    }

    // Logical
    @GetMapping("/unknown")
    @PreAuthorize("hasRole('ROLE_ADMIN') " + " && " +
            "hasAuthority('user:read') ")
    public String testUnknownPermission() {

        return "This should NEVER work";
    }

}
