package com.krillinator.springSecurityLektion.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserModelService implements UserDetailsService {

    private final UserModelRepository userModelRepository;

    @Autowired
    public UserModelService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // TODO - Exception Handling

        return userModelRepository.findByUsername(username);    // Query
    }

    // TODO - Save User

}
