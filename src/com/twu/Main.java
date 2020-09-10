package com.twu;

import com.twu.page.AdminPage;
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
        Page adminPage = new AdminPage(userContainer, eventRankingList);

        AdminUser adminUser = new AdminUser("admin", "123");
        userContainer.registerUser(adminUser);

        uiController.registerPages(homePage, userPage, adminPage);
        uiController.run();
    }
}
