package com.semi.gamespace.main.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandler implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null) {
            int errorCode = Integer.parseInt(status.toString());
            if(errorCode == HttpStatus.NOT_FOUND.value()) {
                return "common/error/error404";
            } else
            if(errorCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                return "common/error/error405";
            }
        }
        return "common/error/error";
    }
}
