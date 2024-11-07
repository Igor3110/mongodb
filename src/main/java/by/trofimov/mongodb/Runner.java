package by.trofimov.mongodb;

import java.util.List;
import by.trofimov.mongodb.entity.User;
import by.trofimov.mongodb.service.UserService;
import by.trofimov.mongodb.service.UserServiceImpl;

/**
 * @author Igor Trofimov
 */
public class Runner {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        User newUser = new User("Andrew", 36);

        //Add new user
        userService.create(newUser);

        //Find by id
        System.out.println("Found user by id:");
        User userById = userService.getById("672cedaaaa4dccd3df072f8a");
        System.out.println(userById);

        //Find by name
        System.out.println("Found user by name:");
        User userByName = userService.getByName("Igor");
        System.out.println(userByName);

        //Update user
        userByName.setAge(34);
        userService.update(userByName);

        //Delete user
        userService.delete(newUser);

        //Find all users
        System.out.println("All users:");
        List<User> users = userService.getAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

}
