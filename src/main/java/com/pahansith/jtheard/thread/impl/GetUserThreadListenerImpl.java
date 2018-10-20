package com.pahansith.jtheard.thread.impl;

import com.pahansith.jtheard.model.User;
import com.pahansith.jtheard.service.ServiceMediator;
import com.pahansith.jtheard.service.UserService;
import com.pahansith.jtheard.thread.ThreadListener;

/**
 * Custom Implementation of the ThreadListener interface
 * specified for register callbacks for get users on separate thread function
 */
public class GetUserThreadListenerImpl implements ThreadListener {

    @Override
    public void onThreadComplete(Object obj) {
        if (null != obj) {
            if (obj instanceof User) {
                User user = (User) obj;
                /*Do whatever task you want to do with User object retrieved from web api*/
                try {
                    UserService service = (UserService) ServiceMediator.getInstance()
                            .getService(UserService.class);

                    service.receiveDataFromAnotherThread(user);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void onThreadReturnsError(Exception e) {
        try {
            UserService service = (UserService) ServiceMediator.getInstance()
                    .getService(UserService.class);
            service.receiveDataFromAnotherThread(e);
        } catch (IllegalAccessException | InstantiationException e1) {
            e1.printStackTrace();
        }
    }
}
