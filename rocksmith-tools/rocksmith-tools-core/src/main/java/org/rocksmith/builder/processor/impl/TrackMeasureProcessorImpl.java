package org.rocksmith.builder.processor.impl;

import org.rocksmith.builder.processor.ITrackProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yroffin.rocksmith.model.IMeasure;
import org.yroffin.rocksmith.model.ISongEntity;
import org.yroffin.rocksmith.model.impl.MeasureEntity;

import dguitar.adaptors.song.Song;
import dguitar.adaptors.song.SongEvent;
import dguitar.adaptors.song.SongMeasure;
import dguitar.adaptors.song.SongMeasureTrack;
import dguitar.adaptors.song.SongTrack;
import dguitar.adaptors.song.event.SongNotationMessage;
import dguitar.adaptors.song.event.SongNoteOffMessage;
import dguitar.adaptors.song.event.SongNoteOnMessage;
import dguitar.adaptors.song.event.SongTieMessage;

public class TrackMeasureProcessorImpl implements ITrackProcessor {
	final static Logger logger = LoggerFactory
			.getLogger(TrackMeasureProcessorImpl.class);

	private final ISongEntity rocksmithSong;
	private IMeasure currentMeasure = null;

	private final Song song;

	private int[] stringTuning;

	private SongTrack track;

	public TrackMeasureProcessorImpl(Song song, ISongEntity rocksmithSong) {
		this.rocksmithSong = rocksmithSong;
		this.song = song;
	}

	public void process(int countMeasures, double timestamp, SongMeasure measure)
			throws Exception {
		/**
		 * for rocksmith first measure is always COUNT add a custom measure at
		 * beginning if needed
		 */
		String markerName = null;
		if (countMeasures == 0 && measure.getMarker() != null) { throw new Exception(
				"Add a first measure without marker !!!"); }
		if (countMeasures == 0) {
			markerName = "COUNT";
		} else {
			if (measure.getMarker() != null) markerName = measure.getMarker()
					.getName();
		}
		/**
		 * add this measure
		 */
		currentMeasure = rocksmithSong.add(MeasureEntity.factory(song
				.getTempo().getBPM(),
				measure.getTimeSignature().getNumerator(), markerName));
	}

	public void process(int index, SongMeasureTrack track) {
		this.track = track.getTrack();
		this.stringTuning = track.getTrack().getStringTuning();
	}

	public void process(SongEvent event, SongNoteOnMessage message) {
	}

	public void process(SongEvent event, SongNoteOffMessage message) {
		System.err.println(event);
	}

	public void process(SongEvent event, SongTieMessage message) {
		System.err.println(event);
	}

	public void process(SongEvent event, SongNotationMessage message) {
		System.err.println(event);
	}

	public void commit(double bpm, double timestamp) {
		/**
		 * add always an end measure
		 */
		rocksmithSong.add(MeasureEntity.factory(song.getTempo().getBPM(), 4,
				"END"));
		double beat = (60. / bpm) * 4;
		timestamp += beat;
		for (; timestamp < rocksmithSong.getSongLength() + beat; timestamp += beat) {
			rocksmithSong.add(MeasureEntity.factory(song.getTempo().getBPM(),
					4, null));
		}
	}
}
