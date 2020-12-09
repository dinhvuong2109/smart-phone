package com.smartphone.utils;

import com.smartphone.constant.SystemConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Component
public class MessageUtil {
    public static void showMessage(ModelAndView mav, HttpServletRequest req) {
        String message=req.getParameter("message");
        if(message != null) {
            String messageResponse="";
            String alert="";
            switch (message) {
                case SystemConstant.INSERTSUCCESS:
                    messageResponse = "Insert success";
                    alert = "success";
                    break;
                case SystemConstant.UPDATESUCCESS:
                    messageResponse = "Update success";
                    alert = "success";
                    break;
                case SystemConstant.DELETESUCCESS:
                    messageResponse = "Delete success";
                    alert = "success";
                    break;
                case SystemConstant.ERRORSYSTEM:
                    messageResponse = "Error system";
                    alert = "danger";
                    break;
                default:
                    messageResponse= "Error message";
                    alert = "danger";
                    break;
            }
            mav.addObject("messageResponse", messageResponse);
            mav.addObject("alert", alert);
        }
    }
}