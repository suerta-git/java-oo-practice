package com.twu.page;

import com.twu.Event;
import com.twu.EventRankingList;
import com.twu.User;
import com.twu.UserContainer;

import java.util.Scanner;

public class UserPage extends Page {
    private final UserContainer userContainer;
    private final EventRankingList eventRankingList;
    private final Scanner scanner = new Scanner(System.in);
    private User user;

    public UserPage(UserContainer userContainer, EventRankingList eventRankingList) {
        super("/home/user");
        this.userContainer = userContainer;
        this.eventRankingList = eventRankingList;
    }

    @Override
    public String doAndGetNext() {
        String userName = getUser();
        System.out.print(formatOutput(
                "欢迎 " + userName + "\n" +
                "1. 查看热搜排行榜\n" +
                "2. 给热搜事件投票\n" +
                "3. 购买热搜\n" +
                "4. 添加热搜\n" +
                "5. 回到上一页\n" +
                "0. 退出\n" +
                "请输入选项代码：")
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
                showRank();
                return getPath();
            case 2:
                vote();
                return getPath();
            case 3:
                buyRank();
                return getPath();
            case 4:
                addEvent();
                return getPath();
            case 5:
                user = null;
                return "/home";
        }

        System.out.println("选项错误，请重新输入！");
        return getPath();
    }

    private String getUser() {
        if (user == null) {
            System.out.print(formatOutput("请输入用户名："));
            String userName = scanner.nextLine().trim();
            user = userContainer.getUser(userName);
            return userName;
        }
        return user.getUserName();
    }

    private void buyRank() {

    }

    private void addEvent() {
        System.out.print(formatOutput("请输入热搜事件描述："));
        String describe = scanner.nextLine().trim();
        if (eventRankingList.contains(describe)) {
            System.out.println("添加失败！事件已存在");
            return;
        }
        eventRankingList.add(describe);
        System.out.println("添加成功！");
    }

    private void vote() {
        if (user.getBallots() <= 0) {
            formatPrintln("抱歉，您的投票次数已用完！");
            return;
        }

        System.out.print(formatOutput("请输入热搜事件描述："));
        Event event;
        while (true) {
            String describe = scanner.nextLine().trim();
            try {
                event = eventRankingList.get(describe);
            } catch (IllegalArgumentException e) {
                System.out.print(formatOutput("事件不存在，请重新输入："));
                continue;
            }
            break;
        }

        System.out.print(formatOutput(String.format("请输入投票数（当前您还有%d票）：", user.getBallots())));

        int ballots;

        while (true) {
            try {
                ballots = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print(formatOutput("输入格式错误，应输入1-10之间的整数，请重新输入："));
                continue;
            }

            if (ballots <= 0 || ballots > 10) {
                System.out.print(formatOutput("输入格式错误，应输入1-10之间的整数，请重新输入："));
                continue;
            }
            break;
        }

        if (user.getBallots() < ballots) {
            formatPrintln("投票失败，剩余票数不足！");
            return;
        }

        user.vote(ballots);
        eventRankingList.voteEvent(event, ballots);
        formatPrintln(String.format("投票成功！您的剩余票数为%d票", user.getBallots()));
    }

    private void showRank() {
        System.out.print(formatOutput(eventRankingList.showRank()));
    }
}
