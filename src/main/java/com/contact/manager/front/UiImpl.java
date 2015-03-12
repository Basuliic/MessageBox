package com.contact.manager.front;


import com.contact.manager.api.UI;

import java.io.FileNotFoundException;
import java.util.List;

public class UiImpl extends UI {

    @Override
    public void welcome() {
        System.out.println(WELCOME);
    }

    @Override
    public void chooseOut() {
        System.out.println(CHOOSE_OUT);
        int i = Integer.parseInt(console.readLine());
        if (i == 2) //сохранение в XML файл
            request.setWriteXml(true);
    }

    @Override
    public void signIn() {
        System.out.println(SIGN_IN);
        String userName = console.readLine();
        request.setUserName(userName);

    }

    @Override
    public void menu() throws FileNotFoundException {
        System.out.println(MENU);
        int i = Integer.parseInt(console.readLine());
        switch (i) {
            case 1:
                service.getOwnAdvert(request);
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
                setText();
                service.changeAdvert(request);
                break;

            case 7:
                close = true;
                break;

        }


    }

    private void setTopic() throws FileNotFoundException {
        try {
            List<String> list = service.getTopics().getTopicsList();
            System.out.println("Выберите рубрику:");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%2d - %s", i + 1, list.get(i));
            }
            int topic = Integer.parseInt(console.readLine());
            request.setTopic(list.get(topic));
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл с рубриками");
            throw e;
        }
    }

    private void setTitle() {
        System.out.println("Введите заголовок:");
        String s = console.readLine();
        request.setTitle(s);
    }

    private void setText() {
        System.out.println("Введите текст:");
        String s = console.readLine();
        request.setText(s);
    }
}
