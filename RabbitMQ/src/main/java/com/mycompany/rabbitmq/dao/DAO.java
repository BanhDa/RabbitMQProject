/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Vantu
 */
public class DAO {
    
    private static MongoClient mongo;                                                    //ITS#23493
    private static MongoDatabase db;

    static {
        try {
            ServerAddress sa = new ServerAddress("localhost");
//            MongoCredential credential = MongoCredential.createCredential(              //ITS#23493
//                    CommonConfig.DB_USER,                                               //ITS#23493
//                    CommonConfig.DB_AUTHEN_DB,                                          //ITS#23493
//                    CommonConfig.DB_PASSWORD.toCharArray());                            //ITS#23493
            MongoClientOptions mongoOptions                                             //ITS#23493
                    = new MongoClientOptions.Builder()                                  //ITS#23493
//                            .connectionsPerHost(CommonConfig.DB_CONNECTION_PER_HOST)    //ITS#23493
                            .build();                                                   //ITS#23493
            mongo = new MongoClient(sa, mongoOptions);       //ITS#23493
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final String CHAT_LOG_EXTENSION = "chatlogextension";
    public static final String CHAT_LOG_EXTENSION_2 = "chatlogextension_2";
    public static final String CHAT_LOG_EXTENSION_3 = "chatlogextension_3";
    
    private static final List<String> DBS = Arrays.asList(CHAT_LOG_EXTENSION, CHAT_LOG_EXTENSION_2, CHAT_LOG_EXTENSION_3);
    private static final Map<String, String> COLLECTIONS = new HashMap<>();
    
    public static void init() {
        COLLECTIONS.put(CHAT_LOG_EXTENSION, "56f49d6be4b0710464fbfb63");
        COLLECTIONS.put(CHAT_LOG_EXTENSION_2, "55e0414c993234cd044b3c91");
        COLLECTIONS.put(CHAT_LOG_EXTENSION_3, "58d0f4f4e4b0dcb2c17daf4b");
    }
    
    public static void save(String message) {
        for (int i = 0; i < 3; i++) {
            for (String dbName : DBS) {
                changeDB(dbName);
                String collectionName = COLLECTIONS.get(dbName);
                MongoCollection collection = db.getCollection(collectionName);

                Document query = new Document("test", new Document("$exists", true));

                Document update = new Document("$set", new Document("test", message));
                UpdateOptions updateOptions = new UpdateOptions();
                updateOptions.upsert(true);
                collection.updateMany(query, update, updateOptions);
            }
        }
    }
    
    private static void changeDB(String dbName) {
        db = mongo.getDatabase(dbName);
    }
}
