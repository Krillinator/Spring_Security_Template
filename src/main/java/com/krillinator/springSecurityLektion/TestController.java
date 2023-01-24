package com.krillinator.springSecurityLektion;

import com.krillinator.springSecurityLektion.configurations.AppPasswordConfig;
import com.krillinator.springSecurityLektion.user.UserModel;
import com.krillinator.springSecurityLektion.user.UserModelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/** For debugging ONLY */

@Controller
public class TestController {

    private final UserModelRepository userModelRepository;
    private final AppPasswordConfig appPasswordConfig;

    @Autowired
    public TestController(UserModelRepository userModelRepository, AppPasswordConfig appPasswordConfig) {
        this.userModelRepository = userModelRepository;
        this.appPasswordConfig = appPasswordConfig;
    }



    @GetMapping("/register")
    public String displayRegisterUser(UserModel userModel) {    // THIS ARGUMENT MUST EXIST

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "register";
        }

        // Prepare Model for persistence
        userModel.setPassword(appPasswordConfig.bCryptPasswordEncoder().encode(userModel.getPassword()));
        userModel.setAccountNonExpired(true);
        userModel.setAccountNonLocked(true);
        userModel.setCredentialsNonExpired(true);
        userModel.setEnabled(true);

        // IF no errors
        System.out.println(userModel);
        userModelRepository.save(userModel);
        // model.addAttribute("user", userModel);

        return "home";
    }


}
