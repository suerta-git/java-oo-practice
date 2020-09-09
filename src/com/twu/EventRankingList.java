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
}
