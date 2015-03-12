package com.contact.manager.dao;

import com.contact.manager.dao.model.Topics;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Created by ^_^ on 12.03.2015.
 */
public class TopicsDao {
    private static String PATH = "date/topics.json";
    private static Gson gson = new Gson();
    private static TopicsDao dao;

    private TopicsDao() {}

    public static TopicsDao getTopicsDao(){
        if (dao == null) {
            dao = new TopicsDao();
        }
        return dao;
    }

    public Topics getTopics() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(PATH));

        //convert the json string back to object
        Topics topics = gson.fromJson(br, Topics.class);
        return topics;
    }
}
