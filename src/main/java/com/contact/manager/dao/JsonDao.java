package com.contact.manager.dao;

import com.contact.manager.api.AdvertDao;
import com.contact.manager.api.Request;
import com.contact.manager.dao.model.Advertisement;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ^_^ on 11.03.2015.
 */
public class JsonDao implements AdvertDao {
    private static Gson gson = new Gson();
    private static String PATH = "";
    private static JsonDao dao;

    private JsonDao() {}

    public static JsonDao getJsonDao(){
        if (dao == null) {
            dao = new JsonDao();
        }
        return dao;
    }


    @Override
    public List<Advertisement> readOwnAdverts(Request request){
        ArrayList<Advertisement> result = new ArrayList<>();

        return result;
    }

    @Override
    public List<Advertisement> readAdvertsByTopic(Request request){
        ArrayList<Advertisement> result = new ArrayList<>();

        return result;
    }

    @Override
    public List<Advertisement> readAdvertsByAuthor(Request request){
        ArrayList<Advertisement> result = new ArrayList<>();

        return result;
    }

    @Override
    public void saveAdvert(Request request){

    }
    @Override
    public void deleteAdvert(Request request){

    }
    @Override
    public void changeAdvert(Request request){

    }
}
