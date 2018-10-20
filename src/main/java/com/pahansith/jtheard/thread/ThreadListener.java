package com.pahansith.jtheard.thread;

import com.pahansith.jtheard.model.User;

public interface ThreadListener {
    void onThreadComplete(Object object);
    void onThreadReturnsError(Exception e);
}
