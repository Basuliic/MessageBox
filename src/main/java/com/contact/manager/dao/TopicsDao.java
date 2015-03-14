package com.contact.manager.dao;

import com.contact.manager.dao.model.Topic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO для рубрик
 */
public class TopicsDao {
    private static final String
            PATH = "data/topics.json",
            ENCODING = "CP1251",
            TOPICS = "[\n" +
                    "{\"topic\":\"продажа\"},\n" +
                    "{\"topic\":\"покупка\"},\n" +
                    "{\"topic\":\"аренда\"},\n" +
                    "{\"topic\":\"услуги\"},\n" +
                    "{\"topic\":\"знакомства\"}\n" +
                    "]";
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static TopicsDao dao;

    private TopicsDao() {
    }

    /**
     * Метод получения экземпляра класса TopicsDao
     *
     * @return экземпляр класса TopicsDao
     */
    public static TopicsDao getTopicsDao() {
        if (dao == null) {
            dao = new TopicsDao();
        }
        return dao;
    }

    /**
     * Метод получения рубрик из файла
     *
     * @return Лист рубрик
     * @throws FileNotFoundException - если файл не обнаружен
     */
    @SuppressWarnings("unchecked")
    public List<Topic> getTopics() throws FileNotFoundException {
        List<Topic> topics = new ArrayList<>(1);
        try (InputStreamReader br = new InputStreamReader(new FileInputStream(PATH), ENCODING)) {
            //convert the json string back to object
            Type type = new TypeToken<List<Topic>>() {
            }.getType();
            topics = gson.fromJson(br, type);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topics;
    }

    /**
     * Создаёт новый файл рубрик
     */
    public static void init() {
        File file = new File(PATH);
        file.getParentFile().mkdirs();
        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(TOPICS.getBytes(ENCODING));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
