package com.easyArch.util;

public class NetException extends RuntimeException{
    private static final long serialVersionUID=4321L;

    public NetException(String message) {
        super(message);
    }

    public NetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetException(Throwable cause) {
        super(cause);
    }
}
