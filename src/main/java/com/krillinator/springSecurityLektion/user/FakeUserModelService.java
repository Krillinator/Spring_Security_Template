package com.krillinator.springSecurityLektion.user;

import com.krillinator.springSecurityLektion.user.dataObjects.IUserModelDAO;
import org.springframework.stereotype.Service;

@Service("name_fake")
public class FakeUserModelService implements IUserModelDAO<UserModel> {

    @Override
    public UserModel findUser(String username) {
        return null;
    }

    @Override
    public void save(UserModel userModel) {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
