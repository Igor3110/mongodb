package by.trofimov.mongodb.mapper;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import lombok.experimental.UtilityClass;
import by.trofimov.mongodb.entity.User;

@UtilityClass
public class UserMapper {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    public static DBObject toDbObject(User user) {
        DBObject document = new BasicDBObject();
        if (user.getId() != null) {
            document.put(ID, user.getId());
        }
        if (user.getName() != null) {
            document.put(NAME, user.getName());
        }
        if (user.getAge() != null) {
            document.put(AGE, user.getAge());
        }
        return document;
    }

    public static User toUser(DBObject dbObject) {
        User user = new User();
        if (dbObject.containsField(ID) && dbObject.get(ID) instanceof ObjectId) {
            ObjectId id = (ObjectId) dbObject.get(ID);
            user.setId(id);
        }
        if (dbObject.containsField(NAME) && dbObject.get(NAME) instanceof String) {
            String name = (String) dbObject.get(NAME);
            user.setName(name);
        }
        if (dbObject.containsField(AGE) && dbObject.get(AGE) instanceof Integer) {
            Integer age = (Integer) dbObject.get(AGE);
            user.setAge(age);
        }
        return user;
    }

}
