package com.jmhz.devicemanage.callback;

import com.google.zxing.Result;

/**
 * Interface for decoding barcode.
 * @author ZengZhiPeng
 */
public interface OnDecodeSuccess {
	/**
	 * Return the result of the barcode when decoding success.
	 * @param rawResult Content of the barcode including text,type,format and so on.
	 */
	public void handleDecode(Result rawResult);
}
