package com.contact.manager.dao.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ^_^ on 12.03.2015.
 */
public class Advertisement {
    String author,
            topic,
            title,
            text;
    long time;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return String.format("-------------\n" +
                "Рубрика: %s\n" +
                "Автор: %s\n" +
                "Заголовок: %s\n" +
                "Текст: %s\n" +
                "Дата изменения: %s\n"
                , topic, author, title, text, dateFormat.format(new Date(time * 1000)));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Advertisement))
            return false;
        Advertisement advert = (Advertisement) obj;
        if (advert.getAuthor().equals(this.author))
            if (advert.getTitle().equals(this.title))
                if (advert.getTopic().equals(this.topic))
                    return true;
        return false;
    }
}
