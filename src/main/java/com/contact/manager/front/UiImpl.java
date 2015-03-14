package com.contact.manager.front;


import com.contact.manager.api.Request;
import com.contact.manager.api.UI;
import com.contact.manager.dao.model.Advertisement;
import com.contact.manager.dao.model.Topic;
import com.contact.manager.service.FileService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class UiImpl implements UI {
    private boolean writeXml;
    private boolean close;
    private Request request = new Request();
    private Scanner console = new Scanner(System.in);
    private FileService service;
    private static final String
            WELCOME = "Добро пожаловать на доску объявлений!\n",
            CHOOSE_OUT = "Пожалуйста, выберите тип вывода данных\n" +
                    "1 - JSON\n" +
                    "2 - XML\n",
            CHOOSE_OUT_ERR = "Введено неверное значение, используется параметр по умолчанию <JSON>",
            CHOOSE_MENU_ERR = "Введено неверное значение",
            MENU = "Выберите действие:\n" +
                    "1 - Просмотр своих объявлений\n" +
                    "2 - Просмотр объявлений в рубрике\n" +
                    "3 - Просмотр объявлений автора\n" +
                    "4 - Новое объявление\n" +
                    "5 - Редактировать объявлений\n" +
                    "6 - Удалить объявление\n" +
                    "7 - Выйти\n",
            CHOOSE_AUTHOR = "Введите имя пользователя:",
            AUTHOR_ERR = "Введено неверное имя пользователя необходимо от 4 до 20 символов, " +
                    "латиницей или цифрами, первый символ всегда должен быть буквой)",
            CHOOSE_TOPIC = "Выберите рубрику:",
            FILE_CREATE = "Создан стандартный файл",
            SET_TITLE = "Введите заголовок(от 10 до 30 символов):",
            TITLE_ERR = "Неверный объём текста. Введите заново",
            SET_TEXT = "Введите текст(от 20 до 400 символов). Наберите /q для завершения",
            END_OF_TEXT = "/q",
            ADVERTS_ERR = "Не найден файл объявлений",
            TEXT_ERR = "Неверный объём текста. Введите заново",
            NO_ADVERTS = "Не найдено объявлений",
            AUTHOR_REGEX = "^\\p{Alpha}\\w{3,19}";

    public UiImpl(Scanner console) {
        this.console = console;
    }

    public UiImpl() {
    }

    @Override
    public void welcome() {
        System.out.println(WELCOME);
    }

    @Override
    public void chooseOut() {
        System.out.println(CHOOSE_OUT);
        int i;
        try {
            i = Integer.parseInt(console.nextLine());
        } catch (NumberFormatException e) {
            i = 0;
        }
        switch (i) {
            case 1://сохранение в JSON файл
                //используется по умолчанию
                break;
            case 2: //сохранение в XML файл
                writeXml = true;
                break;
            default:
                System.out.println(CHOOSE_OUT_ERR);
        }
    }

    @Override
    public void signIn() {
        request.setUserName(getAuthor());
    }

    @Override
    public void menu() {
        try {
            prepare();
            System.out.println(MENU);
            choose();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isClose() {
        return close;
    }

    @Override
    public void exit() {
        service.saveAll(writeXml);
    }

    private void choose() throws FileNotFoundException {
        List<Advertisement> adverts;
        int i;
        try {
            i = Integer.parseInt(console.nextLine());
        } catch (NumberFormatException e) {
            i = 0;
        }
        switch (i) {
            case 1:
                adverts = service.getOwnAdverts(request);
                printAdverts(adverts);
                break;
            case 2:
                setTopic();
                adverts = service.getAdvertsByTopic(request);
                printAdverts(adverts);
                break;
            case 3:
                setAuthor();
                adverts = service.getAdvertsByAuthor(request);
                printAdverts(adverts);
                break;
            case 4:
                setTopic();
                setTitle();
                setText();
                service.saveAdvert(request);
                break;
            case 5:
                setTopic();
                setTitle();
                if (!service.checkExist(request)) {
                    System.out.println(NO_ADVERTS);
                    break;
                }
                setText();
                service.changeAdvert(request);
                break;
            case 6:
                setTopic();
                setTitle();
                setAuthor();
                service.deleteAdvert(request);
                break;
            case 7:
                close = true;
                break;
            default:
                System.out.println(CHOOSE_MENU_ERR);
        }
    }

    /**
     * запись автора в запрос из консоли
     */
    private void setAuthor() {
        String author = getAuthor();
        request.setAuthor(author);
    }

    /**
     * получение имени автора(пользователя) с верификацией из консоли
     *
     * @return имя автора
     */
    private String getAuthor() {
        System.out.println(CHOOSE_AUTHOR);
        String userName = console.nextLine();
        if (userName.matches(AUTHOR_REGEX)) {
            return userName;
        }
        System.out.println(AUTHOR_ERR);
        return getAuthor();
    }

    /**
     * запись рубрики в запрос из консоли
     *
     * @throws FileNotFoundException
     */
    private void setTopic() throws FileNotFoundException {
        List<Topic> list = service.getTopics();
        System.out.println(CHOOSE_TOPIC);
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%2d - %s\n", i + 1, list.get(i).toString());
        }
        int i;
        try {
            i = Integer.parseInt(console.nextLine());
            Topic topic = list.get(i - 1);
            request.setTopic(topic.toString());
        } catch (NumberFormatException e) {
            System.out.println(CHOOSE_MENU_ERR);
            setTopic();
        }
    }

    /**
     * инициация сервисов и файловой структуры
     *
     * @throws FileNotFoundException
     */
    private void prepare() throws FileNotFoundException {
        if (service == null) {
            try {
                service = new FileService();
            } catch (FileNotFoundException e) {
                System.out.println(ADVERTS_ERR);
                System.out.println(FILE_CREATE);
                FileService.init(writeXml);
                service = new FileService();
            }
        }
    }

    /**
     * запись заголовка в запрос из консоли
     */
    private void setTitle() {
        System.out.println(SET_TITLE);
        String s = console.nextLine();
        request.setTitle(s);
        int length = s.length();
        if (length <= 10 || length > 30) {
            System.out.println(TITLE_ERR);
            setTitle();
        }
    }

    /**
     * запись тескта в запрос из консоли
     */
    private void setText() {
        System.out.println(SET_TEXT);
        StringBuilder text = new StringBuilder();
        String s = console.nextLine();
        while (!s.equals(END_OF_TEXT)) {
            boolean quit = false;
            if (s.endsWith(END_OF_TEXT)) {
                s = s.substring(0, s.length() - 2);
                quit = true;
            }
            text.append(s);
            text.append("\n");
            if (quit) break;
            s = console.nextLine();
        }
        int length = text.length();
        request.setText(text.toString());
        if (length <= 20 || length > 400) {
            System.out.println(TEXT_ERR);
            setText();
        }
    }

    /**
     * печать объявлений в консоль
     *
     * @param list лист объявлений на печать
     */
    private void printAdverts(List<Advertisement> list) {
        if (list.size() == 0)
            System.out.println(NO_ADVERTS);
        list.forEach(System.out::println);
    }

    public Scanner getConsole() {
        return console;
    }

    public void setConsole(Scanner console) {
        this.console = console;
    }
}