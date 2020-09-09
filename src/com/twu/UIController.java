package com.twu;

import com.twu.page.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UIController {
    private String nextPagePath = "/home";

    private final Map<String, Page> pageMap = new HashMap<>();

    public void registerPage(Page page){
        pageMap.put(page.getPath(), page);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                nextPagePath = pageMap.get(nextPagePath).doAndGetNext();
            }
        } catch (RuntimeException ignored) {
            System.out.println("==== 退出成功，Bye! ====");
        }
    }
}
