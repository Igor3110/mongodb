package by.trofimov.mongodb.service;

import java.util.List;
import by.trofimov.mongodb.entity.User;

public interface UserService {

    boolean create(User user);

    List<User> getAll();

    User getById(String id);

    User getByName(String name);

    boolean update(User user);

    boolean delete(User user);

}
