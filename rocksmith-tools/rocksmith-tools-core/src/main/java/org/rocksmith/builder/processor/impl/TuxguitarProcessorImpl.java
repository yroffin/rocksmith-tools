package org.rocksmith.builder.processor.impl;

import org.herac.tuxguitar.song.models.TGBeat;
import org.herac.tuxguitar.song.models.TGMeasure;
import org.herac.tuxguitar.song.models.TGNote;
import org.herac.tuxguitar.song.models.TGVoice;
import org.rocksmith.builder.processor.ITuxguitarTrackProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yroffin.rocksmith.model.IMeasure;
import org.yroffin.rocksmith.model.INote;
import org.yroffin.rocksmith.model.ISongEntity;
import org.yroffin.rocksmith.model.impl.MeasureEntity;
import org.yroffin.rocksmith.model.impl.NoteEntity;

public class TuxguitarProcessorImpl implements ITuxguitarTrackProcessor {
	final static Logger logger = LoggerFactory
			.getLogger(TuxguitarProcessorImpl.class);

	private final ISongEntity rocksmithSong;
	private IMeasure currentMeasure = null;

	private TGBeat currentBeat;

	private TGVoice currentVoice;

	public TuxguitarProcessorImpl(ISongEntity rocksmithSong) {
		this.rocksmithSong = rocksmithSong;
	}

	public void process(long timestamp, TGMeasure measure) throws Exception {
		/**
		 * for rocksmith first measure is always COUNT add a custom measure at
		 * beginning if needed
		 */
		String markerName = null;
		if (measure.getNumber() == 0 && measure.getMarker() != null) { throw new Exception(
				"Add a first measure without marker !!!"); }
		if (measure.getNumber() == 0) {
			markerName = "COUNT";
		} else {
			if (measure.hasMarker()) markerName = measure.getMarker()
					.getTitle();
		}
		/**
		 * add this measure
		 */
		currentMeasure = rocksmithSong.add(MeasureEntity.factory(measure
				.getTempo().getValue(), measure.getTimeSignature()
				.getNumerator(), markerName));
		System.err.println("Measure:" + timestamp);
	}

	public void process(long timestamp, TGBeat beat) {
		currentBeat = beat;
	}

	public void process(long timestamp, TGVoice voice) {
		currentVoice = voice;
		System.err.println("  Voice:" + voice.getIndex() + ":" + timestamp
				+ ":" + voice);
	}

	INote[] strings = new INote[6];

	public void process(long timestamp, TGNote note) {
		System.err.println("    Note:" + note.getValue() + ":" + timestamp
				/ 2000. + ":" + note);
		if (timestamp == 49680) {
			int i = 0;
		}
		INote current = null;
		if (!note.isTiedNote() && !note.getEffect().isDeadNote()) {
			/**
			 * store local note
			 */
			current = NoteEntity.factory(timestamp,
					note.getEffect().isBend() ? 1 : 0, note.getValue(), note
							.getEffect().isHammer() ? 1 : 0, note.getEffect()
							.isHarmonic() ? 1 : 0, 0, 0, 0, 0, note.getEffect()
							.isSlide() ? 1 : -1, 6 - note.getString(), 0, 0,
					-1, -1, note.isTiedNote());
			/**
			 * if previous was slide, then add 2 sustain + set slideTo current
			 */
			if (strings[6 - note.getString()] != null
					&& strings[6 - note.getString()].isSlide()) {
				/**
				 * slideTo
				 */
				strings[6 - note.getString()].setSlideTo(note.getValue());
				strings[6 - note.getString()].addSustain(2);
			}
			currentMeasure.add(timestamp, current);
		}
		if (note.isTiedNote() && strings[6 - note.getString()] != null) {
			/**
			 * tied note
			 */
			strings[6 - note.getString()].addSustain(timestamp
					- strings[6 - note.getString()].getTime()
					+ currentVoice.getDuration().getTime());
		}
		if (current != null) strings[6 - note.getString()] = current;
	}
}
