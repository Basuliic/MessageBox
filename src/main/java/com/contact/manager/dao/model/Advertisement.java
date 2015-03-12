package com.contact.manager.dao.model;

import java.time.Instant;

/**
 * Created by ^_^ on 12.03.2015.
 */
public class Advertisement {
    String autor,
            date,
            topic,
            title,
            text;
    long time;

    public Advertisement() {
        time = Instant.EPOCH.getEpochSecond();
    }
}
