package com.contact.manager.dao;

import com.contact.manager.api.AdvertDao;
import com.contact.manager.api.Request;
import com.contact.manager.dao.model.Advertisement;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by ^_^ on 11.03.2015.
 */
public class XmlDao implements AdvertDao {
    private static String PATH = "";
    private static Gson gson = new Gson();
    private static XmlDao dao;

    private XmlDao() {}

    public static XmlDao getXmlDao(){
        if (dao == null) {
            dao = new XmlDao();
        }
        return dao;
    }

    @Override
    public List<Advertisement> readOwnAdverts(Request request) {
        return null;
    }

    @Override
    public List<Advertisement> readAdvertsByTopic(Request request) {
        return null;
    }

    @Override
    public List<Advertisement> readAdvertsByAuthor(Request request) {
        return null;
    }

    @Override
    public void saveAdvert(Request request) {

    }

    @Override
    public void deleteAdvert(Request request) {

    }

    @Override
    public void changeAdvert(Request request) {

    }
}
