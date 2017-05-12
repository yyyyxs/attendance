package com.jmhz.device.sys.exception;

public class UserBlockedException extends UserException {
    public UserBlockedException(String reason) {
        super("user.blocked", new Object[]{reason});
    }
}
