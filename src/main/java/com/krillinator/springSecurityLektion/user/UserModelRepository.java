package com.krillinator.springSecurityLektion.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

}
