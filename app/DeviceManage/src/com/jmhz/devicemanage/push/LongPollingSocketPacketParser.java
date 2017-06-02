
package com.jmhz.devicemanage.push;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import android.util.Log;


/**
 * 推送数据包解析类
 * @author Administrator
 *
 */
public class LongPollingSocketPacketParser {
	private static final String TAG = "LongPollingSocketPacketParser";

	private LongPollingSocketClient mClient;

	private boolean mMasking = true;
	private boolean mClosed = false;

	private static final int BYTE = 255;
	private static final int FIN = 128;
	private static final int MASK = 128;
	
	private static final int OP_TEXT = 1;
	private static final int OP_BINARY = 2;
	private static final int OP_CLOSE = 8;


	public LongPollingSocketPacketParser(LongPollingSocketClient client) {
		mClient = client;
	}

	private static byte[] mask(byte[] payload, byte[] mask, int offset) {
		if (mask.length == 0)
			return payload;

		for (int i = 0; i < payload.length - offset; i++) {
			payload[offset + i] = (byte) (payload[offset + i] ^ mask[i % 4]);
		}
		return payload;
	}

	public void start(HappyDataInputStream stream) throws IOException {
		
		StringBuffer msgBuf = new StringBuffer();
		while (true) {
			String line = stream.readLine();
			if (line == null){
				break;
			}
			else{
				System.out.println("get resp stream time:"+new Date().toLocaleString());
				if(line.startsWith("{")&&line.endsWith("}")){
					mClient.getListener().onMessage(line);
					
				}else if(line.startsWith("{")&&!line.endsWith("}")){
					msgBuf.append(line);
					
				}else if(!line.startsWith("{")&&line.endsWith("}")){
					msgBuf.append(line);
					mClient.getListener().onMessage(msgBuf.toString());
					msgBuf = new StringBuffer();
					Log.d(TAG, msgBuf.toString());
					
				}else{
					Log.d(TAG, "Receiving message:"+line);
				}
			}
		}
		mClient.getListener().onDisconnect(0, "EOF");
	}
	public byte[] frame(String data) {
		return frame(data, OP_TEXT, -1);
	}

	public byte[] frame(byte[] data) {
		return frame(data, OP_BINARY, -1);
	}

	private byte[] frame(byte[] data, int opcode, int errorCode) {
		return frame((Object) data, opcode, errorCode);
	}

	private byte[] frame(String data, int opcode, int errorCode) {
		return frame((Object) data, opcode, errorCode);
	}

	private byte[] frame(Object data, int opcode, int errorCode) {
		if (mClosed)
			return null;

		Log.d(TAG, "Creating frame for: " + data + " op: " + opcode + " err: " + errorCode);

		byte[] buffer = (data instanceof String) ? decode((String) data) : (byte[]) data;
		int insert = (errorCode > 0) ? 2 : 0;
		int length = buffer.length + insert;
		int header = (length <= 125) ? 2 : (length <= 65535 ? 4 : 10);
		int offset = header + (mMasking ? 4 : 0);
		int masked = mMasking ? MASK : 0;
		byte[] frame = new byte[length + offset];

		frame[0] = (byte) ((byte) FIN | (byte) opcode);

		if (length <= 125) {
			frame[1] = (byte) (masked | length);
		} else if (length <= 65535) {
			frame[1] = (byte) (masked | 126);
			frame[2] = (byte) Math.floor(length / 256);
			frame[3] = (byte) (length & BYTE);
		} else {
			frame[1] = (byte) (masked | 127);
			frame[2] = (byte) (((int) Math.floor(length / Math.pow(2, 56))) & BYTE);
			frame[3] = (byte) (((int) Math.floor(length / Math.pow(2, 48))) & BYTE);
			frame[4] = (byte) (((int) Math.floor(length / Math.pow(2, 40))) & BYTE);
			frame[5] = (byte) (((int) Math.floor(length / Math.pow(2, 32))) & BYTE);
			frame[6] = (byte) (((int) Math.floor(length / Math.pow(2, 24))) & BYTE);
			frame[7] = (byte) (((int) Math.floor(length / Math.pow(2, 16))) & BYTE);
			frame[8] = (byte) (((int) Math.floor(length / Math.pow(2, 8))) & BYTE);
			frame[9] = (byte) (length & BYTE);
		}

		if (errorCode > 0) {
			frame[offset] = (byte) (((int) Math.floor(errorCode / 256)) & BYTE);
			frame[offset + 1] = (byte) (errorCode & BYTE);
		}
		System.arraycopy(buffer, 0, frame, offset + insert, buffer.length);

		if (mMasking) {
			byte[] mask = { (byte) Math.floor(Math.random() * 256), (byte) Math.floor(Math.random() * 256), (byte) Math.floor(Math.random() * 256),
					(byte) Math.floor(Math.random() * 256) };
			System.arraycopy(mask, 0, frame, header, mask.length);
			mask(frame, mask, offset);
		}

		return frame;
	}

	public void close(int code, String reason) {
		if (mClosed)
			return;
		mClient.send(frame(reason, OP_CLOSE, code));
		mClosed = true;
	}
	
	public static byte[] decode(String string) {
//		try {
//			return (string).getBytes("UTF-8");
			return (string).getBytes();
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
	}
	
	public static String encode(byte[] buffer) {
        try {
            return new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

	public static class HappyDataInputStream extends DataInputStream {
		public HappyDataInputStream(InputStream in) {
			super(in);
		}

		public byte[] readBytes(int length) throws IOException {
			byte[] buffer = new byte[length];
			readFully(buffer);
			return buffer;
		}
	}
}