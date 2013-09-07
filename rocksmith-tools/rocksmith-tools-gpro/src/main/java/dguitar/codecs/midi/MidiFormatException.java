/*
 * Created on Mar 19, 2005
 */
package dguitar.codecs.midi;

import dguitar.codecs.CodecFormatException;

/**
 * @author Chris
 */
@SuppressWarnings("serial")
public class MidiFormatException extends CodecFormatException {

	/**
	 * @param s
	 */
	public MidiFormatException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param cause
	 */
	public MidiFormatException(String s, Throwable cause) {
		super(s, cause);
	}

}
