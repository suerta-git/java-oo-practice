package com.twu;

public class User {
    private final String userName;

    private int ballots = 10;

    public User(String userName) {
        this.userName = userName;
    }

    public void vote(int ballots) {
        if (getBallots() < ballots) {
            throw new IllegalArgumentException("ballots not enough");
        }
        this.ballots -= ballots;
    }

    public int getBallots() { return ballots; }

    public String getUserName() { return userName; }
}
