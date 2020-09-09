package com.twu;

public class Event implements Comparable<Event> {
    private final String describe;

    private int heat = 0;


    public Event(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public int getHeat() {
        return heat;
    }

    public void vote(int ballots) {
        heat += ballots;
    }

    public boolean equals(Event anotherEvent) {
        return describe.equalsIgnoreCase(anotherEvent.getDescribe());
    }

    @Override
    public int compareTo(Event o) {
        if (this.equals(o)) {
            return 0;
        }
        if (heat < o.getHeat()) {
            return 1;
        }
        return -1;
    }
}
