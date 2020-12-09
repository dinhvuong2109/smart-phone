package com.smartphone.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PageUtil {
    public static void showPage(ModelAndView mav, int page, int totalItems, int size, String sortField, String sortDir) {
        mav.addObject("currentPage", page);
        mav.addObject("totalItems", totalItems);
        mav.addObject("totalPages", (int) Math.ceil((double) totalItems / size));
        mav.addObject("sortField", sortField);
        mav.addObject("sortDir", sortDir);
        mav.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    }

    public static void showPage(ModelAndView mav, int page, int totalItems, int size) {
        mav.addObject("currentPage", page);
        mav.addObject("totalItems", totalItems);
        mav.addObject("totalPages", (int) Math.ceil((double) totalItems / size));
    }
}
