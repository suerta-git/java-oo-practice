package com.twu.page.action;

import com.twu.EventRankingList;
import com.twu.util.FormatPrintable;

import java.util.Scanner;

public interface AddEvent extends FormatPrintable {
    default void addEvent(EventRankingList eventRankingList) {
        System.out.print(formatOutput("请输入热搜事件描述："));
        Scanner scanner = new Scanner(System.in);
        String describe = scanner.nextLine().trim();
        if (eventRankingList.contains(describe)) {
            System.out.println("添加失败！事件已存在");
            return;
        }
        eventRankingList.add(describe);
        System.out.println("添加成功！");
    }
}
