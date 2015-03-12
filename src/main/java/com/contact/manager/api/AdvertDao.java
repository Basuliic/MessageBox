package com.contact.manager.api;

import com.contact.manager.dao.model.Advertisement;

import java.util.List;

/**
 * Created by ^_^ on 12.03.2015.
 */
public interface AdvertDao {
    List<Advertisement> readOwnAdverts(Request request);

    List<Advertisement> readAdvertsByTopic(Request request);

    List<Advertisement> readAdvertsByAuthor(Request request);

    void saveAdvert(Advertisement advert);

    void deleteAdvert(Advertisement advert);

    void changeAdvert(Advertisement advert);
}
