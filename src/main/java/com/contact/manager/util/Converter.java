package com.contact.manager.util;

import com.contact.manager.api.Request;
import com.contact.manager.dao.model.Advertisement;

import java.time.Instant;

/**
 * Конвертер запроса в объявление
 */
public class Converter {
    public static Advertisement convert(Request request) {
        Advertisement result = new Advertisement();
        result.setTopic(request.getTopic());
        result.setTitle(request.getTitle());
        result.setText(request.getText());
        result.setTime(Instant.now().getEpochSecond());
        result.setAuthor(request.getUserName());
        return result;
    }
}
