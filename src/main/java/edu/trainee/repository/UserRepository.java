package edu.trainee.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.trainee.domain.User;

import java.util.List;

/**
 * Created by dennis on 9/15/2015.
 */
public interface UserRepository {
    public User getUserById(Long id);
    public List<User> getAllUsers();
    public Long save(User user);
    public void update(User user);
    public void delete(Integer id);
    public Boolean isExisting(String email);
}
