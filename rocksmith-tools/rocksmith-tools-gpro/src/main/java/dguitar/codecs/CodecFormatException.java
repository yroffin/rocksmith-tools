/*
 * Created on 18/03/2005
 *
 */
package dguitar.codecs;

/**
 * 
 * This abstract class describes a posible format exception when coding/decoding
 * a strem
 * 
 */
@SuppressWarnings("serial")
public abstract class CodecFormatException extends Exception {
	public CodecFormatException(String s) {
		super(s);
	}

	public CodecFormatException(String s, Throwable cause) {
		super(s, cause);
	}
}
