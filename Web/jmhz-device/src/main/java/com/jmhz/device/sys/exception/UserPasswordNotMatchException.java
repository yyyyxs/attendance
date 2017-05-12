package com.jmhz.device.sys.exception;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class UserPasswordNotMatchException extends UserException {

    @SuppressFBWarnings({"NP_NULL_PARAM_DEREF_NONVIRTUAL"})
    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
