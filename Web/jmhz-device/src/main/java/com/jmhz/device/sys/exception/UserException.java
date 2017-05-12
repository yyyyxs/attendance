package com.jmhz.device.sys.exception;

public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }

}
