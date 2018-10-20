package com.pahansith.jtheard.controller;

import com.pahansith.jtheard.model.User;
import com.pahansith.jtheard.service.UserService;
import com.pahansith.jtheard.view.UserView;

public class UserViewController implements AbstractController{
    private UserView view;

    public UserViewController() {
    }

    public UserViewController(UserView view) {
        ControllerMediator.getInstance().registerController(this);
        this.view = view;
    }

    public User getUser(int i) {
        UserService userService = new UserService();
        User user = userService.getUser(1);

        return user;
    }

    public void getUserAsync(int i) {
        UserService userService = new UserService();
        userService.getUserAsynchronously(1);
    }

    @Override
    public void updateController(Object object) {
        view.updateUi(object);
    }

    @Override
    public void updateControllerOnError(String message, Exception e) {
        System.out.println(message);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
