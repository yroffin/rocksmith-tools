package org.rocksmith.builder.processor.impl;

import org.rocksmith.builder.processor.ITrackProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dguitar.adaptors.song.SongEvent;
import dguitar.adaptors.song.SongMeasure;
import dguitar.adaptors.song.SongMeasureTrack;
import dguitar.adaptors.song.event.SongNotationMessage;
import dguitar.adaptors.song.event.SongNoteOffMessage;
import dguitar.adaptors.song.event.SongNoteOnMessage;
import dguitar.adaptors.song.event.SongTieMessage;

public class TrackBeatProcessorImpl implements ITrackProcessor {
	final static Logger logger = LoggerFactory
			.getLogger(TrackBeatProcessorImpl.class);

	public void process(int countMeasures, double timestamp, SongMeasure measure) {
	}

	public void process(int index, SongMeasureTrack track) {
	}

	public void process(SongEvent event, SongNoteOnMessage message) {
		if (minDuration > message.getDuration()) {
			minDuration = message.getDuration();
		}
	}

	public void process(SongEvent event, SongNoteOffMessage message) {
	}

	public void process(SongEvent event, SongTieMessage message) {
		if (minDuration > message.getDuration()) {
			minDuration = message.getDuration();
		}
	}

	public void process(SongEvent event, SongNotationMessage message) {
		if (minDuration > message.getDuration()) {
			minDuration = message.getDuration();
		}
	}

	public void commit(double bpm, double timestamp) {
	}

	int minDuration = 999999999;

	public int getMinDuration() {
		return minDuration;
	}

}
