package com.contact.manager.api;

import java.io.FileNotFoundException;

/**
 * Класс представления
 */
public interface UI {

    public void welcome();

    public void chooseOut();

    public void signIn();

    public void exit();

    public void menu() throws FileNotFoundException;

    public boolean isClose();
}
