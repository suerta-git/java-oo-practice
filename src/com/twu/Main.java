package com.twu;

import com.twu.page.HomePage;
import com.twu.page.Page;
import com.twu.page.UserPage;

public class Main {

    public static void main(String[] args) {
        UserContainer userContainer = new UserContainer();
        EventRankingList eventRankingList = new EventRankingList();
        UIController uiController = new UIController();

        Page homePage = new HomePage();
        Page userPage = new UserPage(userContainer, eventRankingList);

        uiController.registerPages(homePage, userPage);
        uiController.run();
    }
}
