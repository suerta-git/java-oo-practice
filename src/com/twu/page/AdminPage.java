package com.twu.page;

import com.twu.AdminUser;
import com.twu.EventRankingList;
import com.twu.User;
import com.twu.UserContainer;
import com.twu.page.action.AddEvent;
import com.twu.page.action.ShowRank;
import com.twu.util.FormatPrintable;

import java.util.Scanner;

public class AdminPage extends Page implements ShowRank, AddEvent, FormatPrintable {
    private final UserContainer userContainer;
    private final EventRankingList eventRankingList;
    private final Scanner scanner = new Scanner(System.in);
    private AdminUser user;

    public AdminPage(UserContainer userContainer, EventRankingList eventRankingList) {
        super("/home/admin");
        this.userContainer = userContainer;
        this.eventRankingList = eventRankingList;
    }

    @Override
    public String doAndGetNext() {
        String userName;
        try {
            userName = getAdminUser();
        } catch (IllegalArgumentException e) {
            formatPrintln("用户不存在，请重试！");
            return getPath();
        } catch (RuntimeException e) {
            formatPrintln("密码错误，请重试");
            return getPath();
        }

        formatPrint(
                "欢迎 " + userName + "\n" +
                        "1. 查看热搜排行榜\n" +
                        "2. 添加热搜\n" +
                        "3. 添加超级热搜\n" +
                        "4. 回到上一页\n" +
                        "0. 退出\n" +
                        "请输入选项代码："
        );
        int code;
        try {
            code = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            formatPrintln("输入格式错误，请重新输入！");
            return getPath();
        }

        switch (code) {
            case 0:
                exitLoop();
            case 1:
                showRank(eventRankingList);
                return getPath();
            case 2:
                addEvent(eventRankingList);
                return getPath();
            case 3:
                addSuperEvent();
                return getPath();
            case 4:
                user = null;
                return "/home";
        }

        System.out.println("选项错误，请重新输入！");
        return getPath();
    }

    private void addSuperEvent() {
        System.out.print(formatOutput("请输入热搜事件描述："));
        Scanner scanner = new Scanner(System.in);
        String describe = scanner.nextLine().trim();
        if (eventRankingList.contains(describe)) {
            System.out.println("添加失败！事件已存在");
            return;
        }
        eventRankingList.addSuper(describe);
        System.out.println("添加成功！");
    }

    private String getAdminUser() {
        if (user == null) {
            formatPrint("请输入管理员用户名：");
            String userName = scanner.nextLine().trim();
            user = userContainer.getAdminUser(userName);
            System.out.print("请输入密码：");
            String password = scanner.nextLine();
            if (!user.verify(password)) {
                user = null;
                throw new RuntimeException("密码错误！");
            }
            return userName;
        }
        return user.getUserName();
    }
}
