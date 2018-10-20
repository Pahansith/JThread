package com.pahansith.jtheard.thread;

public interface ThreadListener {
    /**
     * Use as a callback on thread completes with or
     * without any errors
     *
     * @param object is abstract type for return inbuilt or user defined object
     */
    void onThreadComplete(Object object);

    /**
     * Use as a callback on thread throws an exception
     * @param e is Abstract type for return inbuilt exceptions or user defined exceptions
     */
    void onThreadReturnsError(Exception e);
}
