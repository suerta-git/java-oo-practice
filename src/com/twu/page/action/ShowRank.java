package com.twu.page.action;

import com.twu.EventRankingList;
import com.twu.util.FormatPrintable;

public interface ShowRank extends FormatPrintable {
    default void showRank(EventRankingList eventRankingList) {
        formatPrint(eventRankingList.showRank());
    }
}
