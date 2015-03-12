package com.contact.manager.api;

/**
 * Created by ^_^ on 11.03.2015.
 */
public class Request {
    private boolean writeXml;
    private boolean exit;
    private String userName;
    private String topic;
    private String title;
    private String text;
    private int action;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isWriteXml() {
        return writeXml;
    }

    public void setWriteXml(boolean writeXml) {
        this.writeXml = writeXml;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
