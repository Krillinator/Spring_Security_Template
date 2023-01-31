package com.krillinator.springSecurityLektion;

import com.krillinator.springSecurityLektion.configurations.AppPasswordConfig;
import com.krillinator.springSecurityLektion.user.UserModel;
import com.krillinator.springSecurityLektion.user.UserModelRepository;
import com.krillinator.springSecurityLektion.user.UserModelService;
import com.krillinator.springSecurityLektion.user.authorities.UserRoles;
import com.krillinator.springSecurityLektion.user.dataObjects.UserModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REMINDER:
 *  Delete this later
 * */

@RestController
@RequestMapping("/rest")
public class TestRestController {

    private final AppPasswordConfig bcrypt;
    private final UserModelRepository userModelRepository;      // TODO - DAO IMPLEMENTATION HERE
    private final UserModelService userModelService;

    @Autowired
    public TestRestController(AppPasswordConfig bcrypt, UserModelRepository userModelRepository, UserModelService userModelService) {
        this.bcrypt = bcrypt;
        this.userModelRepository = userModelRepository;
        this.userModelService = userModelService;
    }

    @GetMapping("/saveBenny")
    public UserModel saveUserBenny() {

        UserModel benny = new UserModel(
                "Benny",
                bcrypt.bCryptPasswordEncoder().encode("123"),
                UserRoles.ADMIN.getGrantedAuthorities(),
                true,
                true,
                true,
                true
        );

        return userModelRepository.save(benny);
    }

    @GetMapping("/find/{username}")
    public UserModelDTO findByUsername(@PathVariable String username) {

        return new UserModelDTO(userModelService.loadUserByUsername(username));
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
