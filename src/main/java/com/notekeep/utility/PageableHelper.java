package com.notekeep.utility;

import org.apache.commons.lang3.StringUtils;

public class PageableHelper {

    private PageableHelper() {
    }

    public static int getPageNumberFromString(String page) {
        try {
            if (StringUtils.isNotEmpty(page)) {
                return Integer.parseInt(page);
            }
        } catch (NumberFormatException e) {
            return 0;
        }
        return 0;
    }
}
