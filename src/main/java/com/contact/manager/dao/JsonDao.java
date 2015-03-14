package com.contact.manager.dao;

import com.contact.manager.dao.model.Advertisement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

/**
 * DAO для объявлений в формате JSON
 */
public class JsonDao {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String
            PATH = "data/adverts.json",
            ENCODING = "CP1251";
    private static JsonDao dao;

    private JsonDao() {
    }

    /**
     * Метод получения экземпляра класса JsonDao
     *
     * @return экземпляр класса JsonDao
     */
    public static JsonDao getJsonDao() {
        if (dao == null) {
            dao = new JsonDao();
        }
        return dao;
    }

    /**
     * Метод получения объявлений из файла
     *
     * @return Лист объявлений
     * @throws FileNotFoundException - если файл не обнаружен
     */
    @SuppressWarnings("unchecked")
    public List<Advertisement> readAdverts() throws FileNotFoundException {
        Scanner in = new Scanner(new File(PATH), ENCODING);
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine())
            sb.append(in.nextLine());

        //convert the json string back to object
        Type type = new TypeToken<List<Advertisement>>() {
        }.getType();
        List<Advertisement> result = gson.fromJson(sb.toString(), type);

        return result;
    }

    /**
     * Метод сохранения объявлений в файл
     */
    public void saveAdverts(List<Advertisement> list) {
        String s = gson.toJson(list);
        File file = new File(PATH);
        file.getParentFile().mkdirs();
        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(s.getBytes(ENCODING));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создаёт новые файлы для сохранения информации
     */
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
