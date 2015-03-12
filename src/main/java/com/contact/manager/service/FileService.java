package com.contact.manager.service;

import com.contact.manager.api.AdvertDao;
import com.contact.manager.api.Request;
import com.contact.manager.dao.JsonDao;
import com.contact.manager.dao.TopicsDao;
import com.contact.manager.dao.XmlDao;
import com.contact.manager.dao.model.Advertisement;
import com.contact.manager.dao.model.Topics;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by ^_^ on 11.03.2015.
 */
public class FileService {
    private static AdvertDao jsonDao = JsonDao.getJsonDao();
    private static AdvertDao xmlDao = XmlDao.getXmlDao();
    private static TopicsDao topicsDao = TopicsDao.getTopicsDao();

    public Topics getTopics() throws FileNotFoundException {
        return topicsDao.getTopics();
    }
    public List<Advertisement> getOwnAdvert(Request request){
        return jsonDao.readOwnAdverts(request);
    }

    public List<Advertisement> readOwnAdverts(Request request) {
        return null;
    }

    public List<Advertisement> readAdvertsByTopic(Request request) {
        return null;
    }

    public List<Advertisement> readAdvertsByAuthor(Request request) {
        return null;
    }

    public void saveAdvert(Request request) {

    }

    public void deleteAdvert(Request request) {

    }

    public void changeAdvert(Request request) {

    }
}
