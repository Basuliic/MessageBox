package com.contact.manager;

import com.contact.manager.api.UI;
import com.contact.manager.front.UiImpl;

public class Main {

    public static void main(String[] args) {
        UI front = new UiImpl();
        front.welcome();
        front.chooseOut();
        front.signIn();

        while (!front.isClose()) {
            front.menu();
        }
        front.exit();
    }
}
