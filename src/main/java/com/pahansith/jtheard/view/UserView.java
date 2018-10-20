package com.pahansith.jtheard.view;

import com.pahansith.jtheard.controller.UserViewController;
import com.pahansith.jtheard.model.User;

public class UserView {

    public UserView() {
        getUser();
        anotherFunction();
        getUserAsync();
        anotherFunction();
        System.out.println("End Main Thread");
    }

    /**
     * This methods indicates a UI process that should run
     * simultaneously with the same UI process without any blocking
     */
    private void anotherFunction() {
        System.out.println("Another UI Function Run");
        for (int i = 0; i < 5; i++) {
            //You can see the main thread still running
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getUserAsync() {
        System.out.println("Asynchronous Mode");
        new UserViewController(this).getUserAsync(1);
    }

    private void getUser() {
        System.out.println("Synchronous Mode");
        User user = new UserViewController(this).getUser(1);

        System.out.println("Name :"+user.getName());
        System.out.println("Age :"+user.getAge());
    }

    public void updateUi(Object object){
        if (object instanceof User) {
            User user = (User)object;
            System.out.println("Name :"+user.getName());
            System.out.println("Age :"+user.getAge());
        }/*Another Object Receive*/

    }
}
