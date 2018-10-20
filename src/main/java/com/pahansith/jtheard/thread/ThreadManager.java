package com.pahansith.jtheard.thread;

import com.pahansith.jtheard.exception.UserNotFoundException;
import com.pahansith.jtheard.model.User;

public class ThreadManager {
    public void getUserDataOnSeparateThread(int userId, ThreadListener listener){
        new Thread(() -> {
            User user = null;
            try {
                /*
                 * Call web API on separate thread and get User
                 * Sleep thread indicates the delay of webapi call
                 * */
                user = new User("George Mc Millan", 25);
                if (null == user) {
                    listener.onThreadReturnsError(new UserNotFoundException("No Such User"));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                /*
                * Catches exceptions thrown in Web API*/
                listener.onThreadReturnsError(e);
            }
            listener.onThreadComplete(user);
        }).start();
    }

    public void getAccountDataOnSeparateThread(int accountId, ThreadListener listener){

    }
}
