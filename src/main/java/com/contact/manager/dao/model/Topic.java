package com.contact.manager.dao.model;

/**
 * Created by ^_^ on 12.03.2015.
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
