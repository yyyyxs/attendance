package com.jmhz.devicemanage.callback;

/**
 * Exception handler.
 * @author ZengZhiPeng.
 * @see {@link #handle(String)}
 */
public interface ErrorHandler {
	/**
	 * Handler the exception with error message.
	 * @param msg Error message.
	 */
	public void handle(String msg);
}
