package com.twu.page;

public abstract class Page {
    private final String path;

    protected Page(String path) {
        this.path = path;
    }

    public abstract String doAndGetNext();

    protected void exitLoop() {
        throw new RuntimeException("exit loop");
    }

    public String getPath() {
        return path;
    }

    protected String formatOutput(String output) {
        return "-----------------------------\n" + output;
    }
}
