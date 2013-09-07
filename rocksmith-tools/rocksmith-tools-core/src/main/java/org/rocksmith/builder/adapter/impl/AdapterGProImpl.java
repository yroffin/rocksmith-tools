package org.rocksmith.builder.adapter.impl;

import java.io.IOException;

import org.rocksmith.builder.adapter.AbstractAdapter;

import dguitar.adaptors.guitarPro.GPAdaptor;
import dguitar.adaptors.song.Song;
import dguitar.codecs.guitarPro.GPFormatException;
import dguitar.codecs.guitarPro.GPInputStream;
import dguitar.codecs.guitarPro.GPSong;

public class AdapterGProImpl extends AbstractAdapter {

	public Song load(String filename) throws AdapterFormatException {
		GPInputStream gpis = null;
		GPSong gpsong;
		try {
			gpis = new GPInputStream(filename);
			gpsong = gpis.readObject();
			gpis.close();
			return GPAdaptor.makeSong(gpsong);
		} catch (GPFormatException e) {
			e.printStackTrace();
			throw new AdapterFormatException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new AdapterFormatException(e);
		}
	}
}
