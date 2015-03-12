package com.contact.manager.api;

import com.contact.manager.service.FileService;

import java.io.Console;
import java.io.FileNotFoundException;

/**
 * Класс представления
 */
public abstract class UI {
    protected static final String
            WELCOME = "Добро пожаловать на доску объявлений!\n",
            CHOOSE_OUT = "Пожалуйста, выберите тип вывода данных\n" +
                    "1 - JSON\n" +
                    "2 - XML\n",
            SIGN_IN = "Введите имя пользователя:",
            MENU = "1 - Просмотр своих объявлений\n" +
                    "2 - Просмотр объявлений в рубрике\n" +
                    "3 - Просмотр объявлений автора\n" +
                    "4 - Новое объявление\n" +
                    "5 - Редактировать объявлений\n" +
                    "6 - Удалить объявление\n" +
                    "7 - Выйти\n";
    protected boolean close;
    protected Request request = new Request();
    protected Console console = System.console();
    protected FileService service = new FileService();


    public abstract void welcome();

    public abstract void chooseOut();

    public abstract void signIn();

    public abstract void menu() throws FileNotFoundException;

    public boolean isClose() {
        return close;
    }
}
