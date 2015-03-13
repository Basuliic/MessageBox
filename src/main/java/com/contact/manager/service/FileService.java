package com.contact.manager.service;

import com.contact.manager.api.Request;
import com.contact.manager.dao.JsonDao;
import com.contact.manager.dao.TopicsDao;
import com.contact.manager.dao.XmlDao;
import com.contact.manager.dao.model.Advertisement;
import com.contact.manager.dao.model.Topic;
import com.contact.manager.util.Converter;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Файловый сервис
 */
public class FileService {
    private static JsonDao jsonDao = JsonDao.getJsonDao();
    private static XmlDao xmlDao = XmlDao.getXmlDao();
    private static TopicsDao topicsDao = TopicsDao.getTopicsDao();
    private List<Advertisement> advertList;
    private List<Topic> topicList;

    public FileService() throws FileNotFoundException {
        advertList = jsonDao.readAdverts();
        topicList = topicsDao.getTopics();
    }

    public List<Topic> getTopics() throws FileNotFoundException {
        return topicList;
    }

    public List<Advertisement> getOwnAdverts(Request request) {
        String author = request.getUserName();
        List<Advertisement> ownAdverts = advertList.stream()
                .filter(p -> p.getAuthor().equals(author))
                .collect(Collectors.toList());
        return ownAdverts;
    }

    public List<Advertisement> getAdvertsByTopic(Request request) {
        String topic = request.getTopic();
        List<Advertisement> advertsByTopic = advertList.stream()
                .filter(p -> p.getTopic().equals(topic))
                .collect(Collectors.toList());
        return advertsByTopic;
    }

    public List<Advertisement> getAdvertsByAuthor(Request request) {
        String author = request.getAuthor();
        List<Advertisement> advertsByAuthor = advertList.stream()
                .filter(p -> p.getAuthor().equals(author))
                .collect(Collectors.toList());
        return advertsByAuthor;
    }

    public boolean checkExist(Request request) {
        String topic = request.getTopic();
        String title = request.getTitle();
        List<Advertisement> advert = advertList.stream()
                .filter(p -> p.getTopic().equals(topic))
                .filter(p -> p.getTitle().equals(title))
                .collect(Collectors.toList());
        return advert.size() == 1;
    }

    public void saveAdvert(Request request) {
        Advertisement advert = Converter.convert(request);
        advertList.add(advert);
    }

    public void deleteAdvert(Request request) {
        Advertisement advert = Converter.convert(request);
        advert.setAuthor(request.getAuthor());
        advertList.remove(advert);
    }

    public void changeAdvert(Request request) {
        String topic = request.getTopic();
        String title = request.getTitle();
        List<Advertisement> adverts = advertList.stream()
                .filter(p -> p.getTopic().equals(topic))
                .filter(p -> p.getTitle().equals(title))
                .collect(Collectors.toList());
        Advertisement advertisement = adverts.get(0);
        advertisement.setText(request.getText());
        advertisement.setAuthor(request.getUserName());
        advertisement.setTime(Instant.now().getEpochSecond());
    }

    public void saveAll(boolean writeXml) {
        jsonDao.saveAdverts(advertList);
        if (writeXml) {
            xmlDao.saveAdverts(advertList);
        }
    }

    public static void init(boolean writeXml) {
        JsonDao.init();
        TopicsDao.init();
        if (writeXml) {
            XmlDao.init();
        }
    }
}
