package com.twu.page;

import com.twu.util.FormatPrintable;

import java.util.Scanner;

public class HomePage extends Page implements FormatPrintable {
    public HomePage() {
        super("/home");
    }

    @Override
    public String doAndGetNext() {
        Scanner scanner = new Scanner(System.in);
        formatPrint(
                "欢迎！\n" +
                "1. 普通用户登录\n" +
                "2. 管理员用户登录\n" +
                "0. 退出\n" +
                "请输入选项代码："
        );

        int code;
        try {
            code = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            formatPrint("输入格式错误，请重新输入：");
            return getPath();
        }

        switch (code) {
            case 0:
                exitLoop();
                break;
            case 1:
                return "/home/user";
            case 2:
                return "/home/admin";
        }

        formatPrint("选项错误，请重新输入：");
        return getPath();
    }
}
