package com.pahansith.jtheard.service;

import com.pahansith.jtheard.controller.ControllerMediator;
import com.pahansith.jtheard.controller.UserViewController;
import com.pahansith.jtheard.exception.UserNotFoundException;
import com.pahansith.jtheard.model.User;
import com.pahansith.jtheard.thread.ThreadListener;
import com.pahansith.jtheard.thread.ThreadManager;
import com.pahansith.jtheard.thread.impl.GetUserThreadListenerImpl;

public class UserService implements AbstractService{

    public Long serviceId = 1L;

    public UserService() {
        ServiceMediator.getInstance().registerService(this);
    }

    public void getUserAsynchronously(int userId){
        System.out.println("Web Call Started Asynchronously");
        /*Creates Listener Object for the newly creating thread*/
        ThreadListener listener = new GetUserThreadListenerImpl();

        //Create new thread and run task on background in non blocking manner
        new ThreadManager().getUserDataOnSeparateThread(1, listener);
    }

    public User getUser(int i) {
        System.out.println("Web Call Started Synchronously");
        /*
        * Call web API on main thread and get User
        * Sleep thread indicates the delay of webapi call
        * */
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new User("George Mc Millan",25);
    }

    @Override
    public synchronized void receiveDataFromAnotherThread(Object object){
        try {

            UserViewController controller = (UserViewController) ControllerMediator.getInstance()
                    .getController(UserViewController.class);

            if (object instanceof Exception) {

                if (object instanceof UserNotFoundException) {
                    UserNotFoundException ex = (UserNotFoundException)object;
                    controller.updateControllerOnError("No Such User Found",ex);
                }

            }

            controller.updateController(object);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserService) {
            UserService userService = (UserService) obj;

            if (this.serviceId.equals(userService.serviceId)) {
                return true;
            }
        }

        return false;
    }
}
