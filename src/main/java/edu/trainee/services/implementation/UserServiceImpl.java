package edu.trainee.services.implementation;

import edu.trainee.domain.User;
import edu.trainee.repository.UserRepository;
import edu.trainee.repository.implementation.JPAUserRepository;
import edu.trainee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dennis on 9/20/2015.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean isExisting(String email) {
        return userRepository.isExisting(email);
    }

    @Override
    public Long save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
