package com.smartphone.utils;

import java.util.List;

public class CheckCategoryUtil {
    public static boolean checkCategoryCode(String categoryCode, List<String> categoryCodeList) {
        for(String item : categoryCodeList) {
            if(item.equals(categoryCode)) return true;
        }
        return false;
    }
}
