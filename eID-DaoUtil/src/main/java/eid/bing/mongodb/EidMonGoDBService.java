package eid.bing.mongodb;

import com.alibaba.fastjson.JSONArray;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @Description:  Connect to mongodb.
 */

public class EidMonGoDBService {

    private static Logger logger = Logger.getLogger(EidMonGoDBService.class.getName());
    private MongoDatabase database;
    private static MongoClient client;

    static {
        List<ServerAddress> mongodbServerHosts = new ArrayList<>();
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        Properties props = new Properties();

        InputStream in = EidMonGoDBService.class.getClassLoader().getResourceAsStream("mongodb.properties");

        try {
            props.load(in);
        } catch (IOException e) {
            logger.warning("loading mongodb.properties file goes wrong..");
        }
        try {
            in.close();
        } catch (IOException e) {
            logger.warning("closing mongodb.properties file goes wrong..");
        }

        builder.connectionsPerHost(50);
        builder.threadsAllowedToBlockForConnectionMultiplier(50);
        builder.maxWaitTime(1000 * 60 * 2);
        builder.connectTimeout(1000 * 30);

        String[] ipString = props.getProperty("ServerIp").split(",");
        int port = Integer.parseInt(props.getProperty("ServerPort"));
        for (String ip : ipString) {
            mongodbServerHosts.add(new ServerAddress(ip, port));
        }

        client = new MongoClient(mongodbServerHosts, builder.build());
    }

    public EidMonGoDBService(String dbName) {
        this.database = client.getDatabase(dbName);
    }

    public synchronized void insertOne(String collectionName, Object object) {

        MongoCollection<Document> collection = database.getCollection(collectionName);
        String json = JSONArray.toJSONString(object);
        collection.insertOne(Document.parse(json));
    }


    public <T> List<T> queryBySql(String collectionName, String sql, Class<T> clazz) {

        List<T> result = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> findIterable = collection.find(BsonDocument.parse(sql));
        for (Document doc : findIterable) {
            String json = doc.toJson();
            result.add(JSONArray.parseObject(json, clazz));
        }
        return result;
    }

}
