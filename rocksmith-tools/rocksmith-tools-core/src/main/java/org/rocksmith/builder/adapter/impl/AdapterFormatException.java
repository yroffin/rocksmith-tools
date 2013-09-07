package org.rocksmith.builder.adapter.impl;

import java.io.IOException;

import dguitar.codecs.guitarPro.GPFormatException;

@SuppressWarnings("serial")
public class AdapterFormatException extends Exception {

	public AdapterFormatException(GPFormatException e) {
		super(e);
	}

	public AdapterFormatException(IOException e) {
		super(e);
	}

}
