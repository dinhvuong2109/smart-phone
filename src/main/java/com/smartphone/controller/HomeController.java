package com.smartphone.controller;

import com.smartphone.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController(value = "homeControllerOfAdmin")
public class HomeController {

    @GetMapping("/user")
    public String getUser() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public ModelAndView getAdmin() {
        ModelAndView mv = new ModelAndView("/admin/admin");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("/login");
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView getLogout(HttpServletRequest request, HttpServletResponse response) {
        //Check authentication
        SecurityUtils.checkAuthentication(request, response);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping(value = "/accessDenied")
    public ModelAndView accessDenied(HttpServletRequest request, HttpServletResponse response) {
        //Check authentication
        SecurityUtils.checkAuthentication(request, response);
        return new ModelAndView("redirect:/login?accessDenied");
    }

    @GetMapping("/test")
    public String test() {
//        ModelAndView mv = new ModelAndView("/admin/category/test");
//        model.addAttribute("message", "Hello");
        return "test";
    }
}
