package edu.trainee.services;

import edu.trainee.domain.User;

/**
 * Created by dennis on 9/20/2015.
 */
public interface UserService {
    public Boolean isExisting(String email);
    public Long save(User user);
}
