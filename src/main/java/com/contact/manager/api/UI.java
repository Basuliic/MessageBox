package com.contact.manager.api;

/**
 * Класс представления
 */
public interface UI {
    /**
     * печать приветствия программы
     */
    public void welcome();

    /**
     * Выбор файлового вывода
     */
    public void chooseOut();

    /**
     * Регистрация пользователя
     */
    public void signIn();

    /**
     * Выход с сохранением
     */
    public void exit();

    /**
     * вывод и работа с меню
     */
    public void menu();

    public boolean isClose();
}
