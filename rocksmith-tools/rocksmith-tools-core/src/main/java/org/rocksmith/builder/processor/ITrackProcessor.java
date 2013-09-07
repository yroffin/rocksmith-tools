package org.rocksmith.builder.processor;

import dguitar.adaptors.song.SongEvent;
import dguitar.adaptors.song.SongMeasure;
import dguitar.adaptors.song.SongMeasureTrack;
import dguitar.adaptors.song.event.SongNotationMessage;
import dguitar.adaptors.song.event.SongNoteOffMessage;
import dguitar.adaptors.song.event.SongNoteOnMessage;
import dguitar.adaptors.song.event.SongTieMessage;

public interface ITrackProcessor {

	void process(int countMeasures, double timestamp, SongMeasure measure)
			throws Exception;

	void process(int index, SongMeasureTrack track);

	void process(SongEvent event, SongNoteOnMessage message);

	void process(SongEvent event, SongNoteOffMessage message);

	void process(SongEvent event, SongTieMessage message);

	void process(SongEvent event, SongNotationMessage message);

	public void commit(double bpm, double timestamp);
}
