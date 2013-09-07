package org.rocksmith.builder.processor;

import org.herac.tuxguitar.song.models.TGBeat;
import org.herac.tuxguitar.song.models.TGMeasure;
import org.herac.tuxguitar.song.models.TGNote;
import org.herac.tuxguitar.song.models.TGVoice;

public interface ITuxguitarTrackProcessor {

	void process(long timestamp, TGMeasure measure) throws Exception;

	void process(long timestamp, TGBeat beat);

	void process(long timestamp, TGVoice voice);

	void process(long timestamp, TGNote note);
}
