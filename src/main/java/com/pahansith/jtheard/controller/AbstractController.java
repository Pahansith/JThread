package com.pahansith.jtheard.controller;

public interface AbstractController {
    void updateController(Object object);

    void updateControllerOnError(String message, Exception e);
}
