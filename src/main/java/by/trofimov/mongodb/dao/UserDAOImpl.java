package by.trofimov.mongodb.dao;

import java.util.List;
import java.util.ArrayList;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.BasicDBObject;
import by.trofimov.mongodb.entity.User;
import by.trofimov.mongodb.mapper.UserMapper;
import by.trofimov.mongodb.util.Connection;
import org.bson.types.ObjectId;

public class UserDAOImpl implements UserDAO {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String CANNOT_BE_ID_WHEN_CREATION_USER = "There cannot be an ID when creating a user";
    public static final String MUST_HAVE_ID_WHEN_UPDATING_USER = "When updating, the user must have an id";
    public static final String MUST_HAVE_ID_WHEN_DELETING_USER = "When deleting, the user must have an id";

    @Override
    public boolean create(User user) {
        if (user.getId() != null) {
            System.err.println(CANNOT_BE_ID_WHEN_CREATION_USER);
            return false;
        }
        DBObject dbObject = UserMapper.toDbObject(user);
        WriteResult result = Connection.getUsersCollection().insert(dbObject);
        ObjectId id = (ObjectId) dbObject.get(ID);
        user.setId(id);
        return result.wasAcknowledged();
    }

    @Override
    public List<User> findAll() {
        DBCursor usersCursor = Connection.getUsersCollection().find();
        List<User> users = new ArrayList<>();
        while (usersCursor.hasNext()) {
            users.add(UserMapper.toUser(usersCursor.next()));
        }
        return users;
    }

    @Override
    public User findById(String id) {
        DBObject query = new BasicDBObject();
        query.put(ID, new ObjectId(id));
        DBObject dbUser = Connection.getUsersCollection().findOne(query);
        return UserMapper.toUser(dbUser);
    }

    @Override
    public User findByName(String name) {
        DBObject query = new BasicDBObject();
        query.put(NAME, (name));
        DBObject dbUser = Connection.getUsersCollection().findOne(query);
        return UserMapper.toUser(dbUser);
    }

    @Override
    public boolean update(User user) {
        if (user.getId() == null) {
            System.err.println(MUST_HAVE_ID_WHEN_UPDATING_USER);
            return false;
        }
        DBObject query = new BasicDBObject();
        query.put(ID, user.getId());
        DBObject modify = Connection.getUsersCollection().findAndModify(query, UserMapper.toDbObject(user));
        return modify != null;
    }

    @Override
    public boolean delete(User user) {
        if (user.getId() == null) {
            System.err.println(MUST_HAVE_ID_WHEN_DELETING_USER);
            return false;
        }
        DBObject query = new BasicDBObject();
        query.put(ID, user.getId());
        WriteResult result = Connection.getUsersCollection().remove(query);
        return result.wasAcknowledged();
    }

}
