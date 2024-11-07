package by.trofimov.mongodb.util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Connection {

    private static final String LOCALHOST = "localhost";
    private static final String TEST = "test";
    private static final String USERS = "users";
    private static final int PORT = 27017;
    private static DB database;

    public static DB getDatabase() {
        if (database == null) {
            MongoClient mongoClient = new MongoClient(LOCALHOST, PORT);
            database = mongoClient.getDB(TEST);
        }
        return database;
    }

    public static DBCollection getUsersCollection() {
        return getDatabase().getCollection(USERS);
    }

}
