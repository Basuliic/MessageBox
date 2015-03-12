package com.contact.manager.api;

/**
 * Created by ^_^ on 11.03.2015.
 */
public abstract class UI {
    protected static final String
            WELCOME = "Добро пожаловать на доску объявлений!\n";
    protected static final String CHOOSE_OUT = "Пожалуйста, выберите тип вывода данных\n" +
                    "1 - JSON\n" +
                    "2 - XML\n";
    protected static final String SIGN_IN = "Введите имя пользователя:";
    protected static final String MENU = "1 - Просмотр своих объявлений\n" +
                    "2 - Просмотр объявлений в рубрике\n" +
                    "3 - Просмотр объявлений автора\n" +
                    "4 - Новое объявление\n" +
                    "5 - Редактировать объявлений\n" +
                    "6 - Удалить объявление\n" +
                    "7 - Выйти\n";
    protected boolean exit;

    public abstract void welcome();

    public abstract void chooseOut();

    public abstract void signIn();

    public abstract void menu();

    public boolean getExit() {
        return exit;
    }
}
