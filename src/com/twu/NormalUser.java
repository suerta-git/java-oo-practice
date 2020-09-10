package com.twu;

public class NormalUser extends User {

    private int ballots = 10;

    public NormalUser(String userName) {
        super(userName);
    }

    public void vote(int ballots) {
        if (getBallots() < ballots) {
            throw new IllegalArgumentException("ballots not enough");
        }
        this.ballots -= ballots;
    }

    public int getBallots() { return ballots; }

}
