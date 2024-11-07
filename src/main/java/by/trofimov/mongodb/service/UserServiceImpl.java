package by.trofimov.mongodb.service;

import java.util.List;
import by.trofimov.mongodb.dao.UserDAO;
import by.trofimov.mongodb.dao.UserDAOImpl;
import by.trofimov.mongodb.entity.User;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean create(User user) {
        return userDAO.create(user);
    }

    @Override
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @Override
    public User getById(String id) {
        return userDAO.findById(id);
    }

    public User getByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userDAO.delete(user);
    }

}
