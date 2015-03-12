package com.contact.manager.front;


import com.contact.manager.service.Request;

import java.io.Console;
import java.util.Locale;
import java.util.ResourceBundle;

public class UiImpl extends com.contact.manager.api.UI {

    private Request request = new Request();
    private ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale("ru"));
    private Console console = System.console();

    @Override
    public void welcome() {
        console.printf("%s", WELCOME);
    }
    @Override
    public void chooseOut() {
        console.printf("%s", CHOOSE_OUT);
        int i = Integer.parseInt(console.readLine());
        if (i == 2)
            request.setWriteXml(true);
    }
    @Override
    public void signIn() {
        console.printf("%s", SIGN_IN);
        String userName = console.readLine();
        request.setUserName(userName);

    }
    @Override
    public void menu() {
        console.printf("%s", MENU);
        int i = Integer.parseInt(console.readLine());
        if (i == 7) {
            exit = true;
            return;
        }
        request.setAction(i);

    }

}
