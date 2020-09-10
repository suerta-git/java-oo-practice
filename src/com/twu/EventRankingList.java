package com.twu;

import com.twu.util.Pair;

import java.util.*;
import java.util.stream.Collector;

public class EventRankingList {
    private final Set<Event> rankingList = new TreeSet<>();
    private final Map<String, Event> eventMap = new HashMap<>();
    private final Map<Integer, Pair<Event, Integer>> paidRankingList = new HashMap<>();

    public int count() {
        return eventMap.size();
    }

    public void buyRank(String describe, int rank, int price) {
        buyRank(get(describe), rank, price);
    }

    public void buyRank(Event event, int rank, int price) {
        if (rank < 1 || rank > count()) {
            throw new IllegalArgumentException(String.format("排名应为1-%d（总数）的整数", count()));
        }
        if (price <= 0) {
            throw new IllegalArgumentException("购买金额至少1元");
        }

        final Pair<Event, Integer> paidEventPair = paidRankingList.get(rank);
        if (paidEventPair != null) {
            if (price <= paidEventPair.getSecond()) {
                throw new IllegalArgumentException("购买金额不足");
            }
            if (!paidEventPair.getFirst().equals(event)) {
                eventMap.remove(paidEventPair.getFirst().getDescribe().toUpperCase());
            }
        }
        rankingList.remove(event);
        paidRankingList.values().removeIf(pair -> pair.getFirst().equals(event));
        paidRankingList.put(rank, new Pair<>(event, price));
    }

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
            throw new IllegalArgumentException("指定事件不存在");
        }
        return eventMap.get(describe.toUpperCase());
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

    private StringBuilder getRankingString() {
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
