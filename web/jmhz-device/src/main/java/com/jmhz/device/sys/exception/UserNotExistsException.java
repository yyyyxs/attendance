package com.jmhz.device.sys.exception;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class UserNotExistsException extends UserException {

    @SuppressFBWarnings({"NP_NULL_PARAM_DEREF_NONVIRTUAL"})
    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
