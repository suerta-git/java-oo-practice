package com.twu;

public class SuperEvent extends Event {

    public SuperEvent(String describe) {
        super(describe);
    }

    @Override
    public void vote(int ballots) {
        heat += 2 * ballots;
    }
}
