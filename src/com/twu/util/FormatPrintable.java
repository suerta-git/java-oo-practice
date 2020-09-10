package com.twu.util;

public interface FormatPrintable {
    default void formatPrint(String output) {
        System.out.print(formatOutput(output));
    }

    default void formatPrintln(String output) {
        System.out.println(formatOutput(output));
    }

    default String formatOutput(String output) {
        return "-----------------------------\n" + output;
    }
}
