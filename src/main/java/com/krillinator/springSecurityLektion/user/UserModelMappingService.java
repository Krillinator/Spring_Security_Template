package com.krillinator.springSecurityLektion.user;

import com.krillinator.springSecurityLektion.user.dataObjects.UserModelDTO;
import org.springframework.stereotype.Service;

@Service
public class UserModelMappingService {

    public UserModelDTO convertEntityToDTO(UserModel userModel) {

        return new UserModelDTO(userModel);
    }

}
