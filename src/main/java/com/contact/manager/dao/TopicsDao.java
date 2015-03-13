package com.contact.manager.dao;

import com.contact.manager.dao.model.Topic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ^_^ on 12.03.2015.
 */
public class TopicsDao {
    private static String PATH = "data/topics.json";
    private static Gson gson = new Gson();
    private static TopicsDao dao;
    private static String ENCODING = "CP1251";

    private TopicsDao() {}

    public static TopicsDao getTopicsDao(){
        if (dao == null) {
            dao = new TopicsDao();
        }
        return dao;
    }

    @SuppressWarnings("unchecked")
    public List<Topic> getTopics() throws FileNotFoundException {
        List<Topic> topics = null;
        try (InputStreamReader br = new InputStreamReader(new FileInputStream(PATH), ENCODING)) {
            //convert the json string back to object
            Type type = new TypeToken<List<Topic>>() {
            }.getType();
            topics = (List<Topic>) gson.fromJson(br, type);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topics;
    }

    public static void init() {
        String s = "[]";
        File file = new File(PATH);
        file.getParentFile().mkdirs();
        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(s.getBytes(ENCODING));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
