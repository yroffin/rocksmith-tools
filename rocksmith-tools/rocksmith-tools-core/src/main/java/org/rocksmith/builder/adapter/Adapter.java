package org.rocksmith.builder.adapter;

import org.rocksmith.builder.adapter.impl.AdapterFormatException;

import dguitar.adaptors.song.Song;

public interface Adapter {
	public Song load(String filename) throws AdapterFormatException;
}
