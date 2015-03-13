package com.contact.manager.dao;

import com.contact.manager.dao.model.Advertisement;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by ^_^ on 11.03.2015.
 */
public class XmlDao {
    private static String PATH = "";
    private static Gson gson = new Gson();
    private static XmlDao dao;

    private XmlDao() {
    }

    public static XmlDao getXmlDao() {
        if (dao == null) {
            dao = new XmlDao();
        }
        return dao;
    }

    public List<Advertisement> readAdverts() {
        return null;
    }

    public void saveAdverts() {
    }

    public static void init() {

    }
}
