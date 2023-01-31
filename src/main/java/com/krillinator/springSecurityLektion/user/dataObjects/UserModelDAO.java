package com.krillinator.springSecurityLektion.user.dataObjects;

import com.krillinator.springSecurityLektion.user.UserModel;
import com.krillinator.springSecurityLektion.user.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component          // Causes ERROR otherwise!!!
public class UserModelDAO implements IUserModelDAO<UserModel> {

    private final UserModelRepository userModelRepository;

    @Autowired
    public UserModelDAO(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserModel findUser(String username) {
        return userModelRepository.findByUsername(username);
    }

    @Override
    public void save(UserModel userModel) {
        userModelRepository.save(userModel);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
