package com.wls.integrateplugs.jpa;

/**
 * Created by wls on 2017/8/24.
 */
import com.wls.projects.diypro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);

}
