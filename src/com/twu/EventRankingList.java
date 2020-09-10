package com.twu;

import java.util.*;

public class EventRankingList {
    private final Set<Event> rankingList = new TreeSet<>();
    private final Map<String, Event> eventMap = new HashMap<>();

    public boolean contains(String describe) {
        return eventMap.containsKey(describe.toUpperCase());
    }

    public boolean contains(Event event) {
        return rankingList.contains(event);
    }

    public void add(String describe) {
        if (contains(describe)) {
            throw new IllegalArgumentException("event has existed");
        }
        Event newEvent = new Event(describe);
        add(newEvent);
        eventMap.put(describe.toUpperCase(), newEvent);
    }

    public void add(Event event) {
        if (contains(event)) {
            throw new IllegalArgumentException("event has existed");
        }
        rankingList.add(event);
    }

    public void addSuper(String describe) {
        if (contains(describe)) {
            throw new IllegalArgumentException("event has existed");
        }
        Event newEvent = new SuperEvent(describe);
        add(newEvent);
        eventMap.put(describe.toUpperCase(), newEvent);
    }

    public Event get(String describe) {
        if (!contains(describe)) {
            throw new IllegalArgumentException("event not exist");
        }
        return eventMap.get(describe.toUpperCase());
    }

    public void voteEvent(String describe, int ballots) {
        voteEvent(get(describe), ballots);
    }

    public void voteEvent(Event event, int ballots) {
        rankingList.remove(event);
        event.vote(ballots);
        add(event);
    }

    public String showRank() {
        if (rankingList.isEmpty()) {
            return "无热搜！\n";
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
