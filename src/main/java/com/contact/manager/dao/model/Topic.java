package com.contact.manager.dao.model;

/**
 * Класс-маппинг рубрики
 */
public class Topic {
    String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return topic;
    }
}
