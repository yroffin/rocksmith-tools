package org.rocksmith.builder.adapter;

import java.io.File;

import org.rocksmith.builder.adapter.impl.AdapterGProImpl;

import dguitar.adaptors.song.Song;

public abstract class AbstractAdapter implements Adapter {

	public static Song instance(String filename) throws Exception {
		File file = new File(filename);
		if (file.exists()) { return new AdapterGProImpl().load(filename); }
		throw new Exception("No adapter for file " + file.getAbsolutePath());
	}

}
