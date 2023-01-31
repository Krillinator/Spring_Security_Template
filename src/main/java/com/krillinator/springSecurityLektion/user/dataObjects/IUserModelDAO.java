package com.krillinator.springSecurityLektion.user.dataObjects;

import com.krillinator.springSecurityLektion.user.UserModel;

public interface IUserModelDAO<T> {

    T findUser(String username);
    void save(UserModel userModel);
    void update();
    void delete();

}


