package com.twu;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class EventRankingList {
    private final Set<Event> rankingList = new TreeSet<>();

    public boolean contains(Event event) {
        return rankingList.contains(event);
    }

    public void add(Event event) {
        if (contains(event)) {
            throw new IllegalArgumentException("event has existed");
        }
        rankingList.add(event);
    }

    public List<Event> getAll() {
        return new ArrayList<>(rankingList);
    }

    public String showRank() {
        if (rankingList.isEmpty()) {
            return "无热搜！";
        }
        return "排名 描述 热度\n" + getRankingString();
    }

    protected StringBuilder getRankingString() {
        StringBuilder result = new StringBuilder();
        int rank = 1;
        for (Event event : rankingList) {
            result.append(rank++)
                    .append(" ")
                    .append(event.getDescribe())
                    .append(" ")
                    .append(event.getHeat())
                    .append("\n");
        }
        return result;
    }
}
