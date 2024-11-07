package by.trofimov.mongodb.dao;

import java.util.List;
import by.trofimov.mongodb.entity.User;

public interface UserDAO {

    boolean create(User user);

    List<User> findAll();

    User findById(String id);

    User findByName(String name);

    boolean update(User user);

    boolean delete(User user);

}
