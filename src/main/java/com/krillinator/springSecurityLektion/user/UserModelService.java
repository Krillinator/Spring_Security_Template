package com.krillinator.springSecurityLektion.user;

import com.krillinator.springSecurityLektion.user.dataObjects.UserModelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserModelService implements UserDetailsService {

    private final UserModelDAO userModelDAO;

    @Autowired
    public UserModelService(UserModelDAO userModelDAO) {        // @Qualifier("name_fake")
        this.userModelDAO = userModelDAO;
    }

    @Override
    public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {

        // TODO - USER EXISTS?                                                              // USER DOES NOT EXIST
        // TODO - REFRESH ERROR PAGE = Property or field 'message' cannot be found on null // WRONG PASSWORD

        return userModelDAO.findUser(username);
    }

    // TODO - Save User
    // TODO - Edit User
    // TODO - DELETE User

}
