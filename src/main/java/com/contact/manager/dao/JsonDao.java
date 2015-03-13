package com.contact.manager.dao;

import com.contact.manager.dao.model.Advertisement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ^_^ on 11.03.2015.
 */
public class JsonDao {
    private static Gson gson = new Gson();
    private static String PATH = "data/adverts.json";
    private static JsonDao dao;

    private JsonDao() {
    }

    public static JsonDao getJsonDao() {
        if (dao == null) {
            dao = new JsonDao();
        }
        return dao;
    }

    @SuppressWarnings("unchecked")
    public List<Advertisement> readAdverts() throws FileNotFoundException {
        List<Advertisement> result = new ArrayList<>();
        Scanner in = new Scanner(new File(PATH), "CP1251");
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine())
            sb.append(in.nextLine());

        //convert the json string back to object
        Type type = new TypeToken<List<Advertisement>>() {
        }.getType();
        result = (List<Advertisement>) gson.fromJson(sb.toString(), type);

        return result;
    }

    public void saveAdverts(List<Advertisement> list) {
        String s = gson.toJson(list);
        File file = new File(PATH);
        file.getParentFile().mkdirs();
        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(s.getBytes("CP1251"));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        String s = "[]";
        File file = new File(PATH);
        file.getParentFile().mkdirs();
        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(s.getBytes("CP1251"));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
