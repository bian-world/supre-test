package com.supretest.commons.exception;

public class MSException extends RuntimeException {

    public MSException(String message) {
        super(message);
    }

    private MSException(Throwable t) {
        super(t);
    }

    public static void throwException(String message) {
        throw new MSException(message);
    }

    public static MSException getException(String message) {
        throw new MSException(message);
    }

    public static void throwException(Throwable t) {
        throw new MSException(t);
    }
}
