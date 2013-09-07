package org.rocksmith.builder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.herac.tuxguitar.gui.TuxGuitar;
import org.herac.tuxguitar.io.base.TGFileFormatManager;
import org.herac.tuxguitar.song.models.TGBeat;
import org.herac.tuxguitar.song.models.TGMeasure;
import org.herac.tuxguitar.song.models.TGMeasureHeader;
import org.herac.tuxguitar.song.models.TGNote;
import org.herac.tuxguitar.song.models.TGSong;
import org.herac.tuxguitar.song.models.TGTrack;
import org.herac.tuxguitar.song.models.TGVoice;
import org.rocksmith.builder.adapter.AbstractAdapter;
import org.rocksmith.builder.processor.ITrackProcessor;
import org.rocksmith.builder.processor.ITuxguitarTrackProcessor;
import org.rocksmith.builder.processor.impl.TrackBeatProcessorImpl;
import org.rocksmith.builder.processor.impl.TrackMeasureProcessorImpl;
import org.rocksmith.builder.processor.impl.TuxguitarProcessorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yroffin.rocksmith.model.ILevel;
import org.yroffin.rocksmith.model.IMeasure;
import org.yroffin.rocksmith.model.INote;
import org.yroffin.rocksmith.model.IPhraseEntity;
import org.yroffin.rocksmith.model.ISongEntity;
import org.yroffin.rocksmith.model.impl.EbeatEntity;
import org.yroffin.rocksmith.model.impl.LevelEntity;
import org.yroffin.rocksmith.model.impl.MeasureEntity;
import org.yroffin.rocksmith.model.impl.PhraseEntity;
import org.yroffin.rocksmith.model.impl.PhraseIterationEntity;
import org.yroffin.rocksmith.model.impl.SectionEntity;
import org.yroffin.rocksmith.model.impl.SongEntity;

import dguitar.adaptors.song.Song;
import dguitar.adaptors.song.SongEvent;
import dguitar.adaptors.song.SongMeasure;
import dguitar.adaptors.song.SongMeasureTrack;
import dguitar.adaptors.song.SongMessage;
import dguitar.adaptors.song.SongPhrase;
import dguitar.adaptors.song.event.SongNotationMessage;
import dguitar.adaptors.song.event.SongNoteOffMessage;
import dguitar.adaptors.song.event.SongNoteOnMessage;
import dguitar.adaptors.song.event.SongTieMessage;

public class RocksmithSongBuilder {
	final static Logger logger = LoggerFactory
			.getLogger(RocksmithSongBuilder.class);

	private int ebeat;
	ISongEntity rocksmithSong = SongEntity.factory();
	ISongEntity rocksmithTrack = SongEntity.factory();

	/**
	 * internal method
	 * 
	 * @param in
	 * @return
	 * @throws Throwable
	 */
	private static InputStream getInputStream(InputStream in) throws Throwable {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int read = 0;
		while ((read = in.read()) != -1) {
			out.write(read);
		}
		byte[] bytes = out.toByteArray();
		in.close();
		out.close();
		out.flush();
		return new ByteArrayInputStream(bytes);
	}

	/**
	 * local url
	 * 
	 * @param url
	 * @return
	 */
	private static boolean isLocalFile(URL url) {
		try {
			if (url.getProtocol().equals(
					new File(url.getFile()).toURI().toURL().getProtocol())) { return true; }
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return false;
	}

	/**
	 * open TuxGuitar file
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws Throwable
	 */
	public TGSong open(final URL url) throws IOException, Throwable {
		InputStream stream = (isLocalFile(url) ? url.openStream()
				: getInputStream(url.openStream()));
		TGSong song = TGFileFormatManager
				.instance()
				.getLoader()
				.load(TuxGuitar.instance().getSongManager().getFactory(),
						stream);
		return song;
	}

	public void load(String filename, URL tgFile) throws Exception {
		/**
		 * load tuxGuitar song
		 */
		TGSong tuxGuitarSongFactory = null;
		try {
			tuxGuitarSongFactory = open(tgFile);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		int countMeasureHeaders = tuxGuitarSongFactory.countMeasureHeaders();
		for (int measureHeaderIndex = 0; measureHeaderIndex < countMeasureHeaders; measureHeaderIndex++) {
			TGMeasureHeader mesureHeader = tuxGuitarSongFactory
					.getMeasureHeader(measureHeaderIndex);
		}

		int countTracks = tuxGuitarSongFactory.countTracks();
		for (int trackIndex = 0; trackIndex < countTracks; trackIndex++) {
			TGTrack track = tuxGuitarSongFactory.getTrack(trackIndex);
		}

		TuxguitarProcessorImpl tuxguitarBeatProcessor = new TuxguitarProcessorImpl(
				rocksmithTrack);
		processTuxguitarTrack(tuxGuitarSongFactory.getTrack(0),
				tuxguitarBeatProcessor);

		/**
		 * simple members
		 */
		rocksmithTrack.setTitle("Simple song");
		rocksmithTrack.setArrangement("bass");
		rocksmithTrack.setPart(1);
		rocksmithTrack.setOffset(0.0);
		rocksmithTrack.setSongLength(259.547);
		rocksmithTrack.setLastConversionDateTime(new Date());
		rocksmithTrack.setArtistName(tuxGuitarSongFactory.getArtist());
		rocksmithTrack.setAlbum(tuxGuitarSongFactory.getAlbum());
		rocksmithTrack.setAlbumYear(tuxGuitarSongFactory.getDate());

		/**
		 * build all needed beat for this song, first evaluate how many mesure
		 * is needed
		 */
		int numerator = 0;
		double currentMeasureTime = 0.;
		double currentBpm = 0.;
		List<IMeasure> measures = rocksmithTrack.getMeasures();
		if (measures.size() > 0) {
			currentBpm = measures.get(0).getBpm();
			currentMeasureTime = 60. / currentBpm;
			numerator = measures.get(0).getNumerator();
		}
		int neededBeats = (int) (rocksmithTrack.getSongLength()
				/ currentMeasureTime + rocksmithTrack.getSongLength()
				% currentMeasureTime) + 1;
		/**
		 * add needed measure
		 */
		int neededMeasures = (neededBeats / numerator - measures.size());
		for (; neededMeasures >= 0; neededMeasures--) {
			measures.add(MeasureEntity.factory(currentBpm, numerator, null));
		}
		/**
		 * build ebeats
		 */
		int currentMeasure = 1;
		double timestamp = 0;
		for (int ebeat = 0; ebeat < neededBeats; timestamp += currentMeasureTime, ebeat++) {
			if ((ebeat % numerator) == 0) {
				rocksmithTrack.add(EbeatEntity.factory(timestamp,
						currentMeasure++));
			} else {
				rocksmithTrack.add(EbeatEntity.factory(timestamp, -1));
			}
		}

		/**
		 * build measures
		 */
		for (IMeasure measure : measures) {
			if (measure.hasMarker()) {
				rocksmithTrack.add(PhraseEntity.factory(0, 0, 0,
						measure.getMarkerName(), 0, measure.getMeasureId()));
				if ("COUNT".compareTo(measure.getMarkerName()) != 0
						&& "END".compareTo(measure.getMarkerName()) != 0) rocksmithTrack
						.add(SectionEntity.factory(measure.getMarkerName(), 1,
								measure.getTime()));
			}
		}

		/**
		 * phrase entities
		 */
		for (IPhraseEntity phrase : rocksmithTrack.getPhrases()) {
			rocksmithTrack.add(PhraseIterationEntity.factory(rocksmithTrack
					.getMeasures().get(phrase.getMeasureId()).getTime(),
					phrase.getId()));
		}

		/**
		 * build levels with notes
		 */
		ILevel level = rocksmithTrack.add(LevelEntity.factory(0));
		for (IMeasure measure : rocksmithTrack.getMeasures()) {
			for (Entry<Double, List<INote>> entry : measure.getNotes()
					.entrySet()) {
				if (entry.getValue().size() == 1) {
					level.add(entry.getValue().get(0));
				}
			}
		}
	}

	public void load(String filename) throws Exception {
		logger.info("Traitement de " + filename);
		Song song = AbstractAdapter.instance(filename);

		/**
		 * details
		 */
		logger.info("Resolution: " + song.getResolution());
		logger.info("Tempo     : " + song.getTempo());
		logger.info("Tracks: " + song.getTrackCount());
		logger.info("Phrases: " + song.getPhraseCount());

		System.err.println(song);
		for (int index = 1; index < song.getTrackCount() + 1; index++) {
			/**
			 * dump track
			 */
			System.err.println(song.getTrack(index));
		}

		/**
		 * simple members
		 */
		rocksmithSong.setTitle("Simple song");
		rocksmithSong.setArrangement("bass");
		rocksmithSong.setPart(1);
		rocksmithSong.setOffset(0.0);
		rocksmithSong.setSongLength(259.547);
		rocksmithSong.setLastConversionDateTime(new Date());

		/**
		 * beat analysis
		 */
		TrackBeatProcessorImpl trackBeatProcessor = new TrackBeatProcessorImpl();
		processTrack(song, 1, 60. / song.getTempo().getBPM(),
				trackBeatProcessor);
		logger.info("Min duration: " + trackBeatProcessor.getMinDuration());

		/**
		 * build measure model
		 */
		TrackMeasureProcessorImpl trackMeasureProcessor = new TrackMeasureProcessorImpl(
				song, rocksmithSong);
		processTrack(song, 1, 60. / song.getTempo().getBPM(),
				trackMeasureProcessor);

		/**
		 * build ebeats
		 */
		double timestamp = 0;
		int currentMeasure = 0;
		ebeat = 100;
		List<IMeasure> measures = rocksmithSong.getMeasures();

		/**
		 * build phrases
		 */
		for (IMeasure measure : measures) {
			if (measure.hasMarker()) {
				rocksmithSong.add(PhraseEntity.factory(0, 0, 0,
						measure.getMarkerName(), 0, measure.getMeasureId()));
				if ("COUNT".compareTo(measure.getMarkerName()) != 0
						&& "END".compareTo(measure.getMarkerName()) != 0) rocksmithSong
						.add(SectionEntity.factory(measure.getMarkerName(), 1,
								measure.getTime()));
			}
		}

		for (IPhraseEntity phrase : rocksmithSong.getPhrases()) {
			rocksmithSong.add(PhraseIterationEntity.factory(rocksmithSong
					.getMeasures().get(phrase.getMeasureId()).getTime(),
					phrase.getId()));
		}

		/**
		 * build levels with notes
		 */
		ILevel level = rocksmithSong.add(LevelEntity.factory(0));
		buildEvents(level);
	}

	public void writeFromTuxguitar(String filename) throws IOException {
		/**
		 * dump result
		 */
		FileWriter fw = new FileWriter(filename);
		fw.write(rocksmithTrack.asXml(new StringBuilder()).toString()
				.replace("\n", "\r\n"));
		fw.close();
	}

	public void writeFromDGuitar(String filename) throws IOException {
		/**
		 * dump result
		 */
		FileWriter fw = new FileWriter(filename);
		fw.write(rocksmithSong.asXml(new StringBuilder()).toString()
				.replace("\n", "\r\n"));
		fw.close();
	}

	private void buildEvents(ILevel level) {
		for (IMeasure measure : rocksmithSong.getMeasures()) {
			for (Entry<Double, List<INote>> entry : measure.getNotes()
					.entrySet()) {
				if (entry.getValue().size() == 1) {
					level.add(entry.getValue().get(0));
				}
			}
		}

	}

	private void processTuxguitarTrack(TGTrack tgTrack,
			ITuxguitarTrackProcessor processor) throws Exception {
		/**
		 * measures
		 */
		long timestamp = 0;
		int countMeasure = tgTrack.countMeasures();
		for (int measureIndex = 0; measureIndex < countMeasure; measureIndex++) {
			TGMeasure measure = tgTrack.getMeasure(measureIndex);
			processor.process(timestamp, measure);

			/**
			 * beats
			 */
			int countBeats = measure.countBeats();
			for (int beatIndex = 0; beatIndex < countBeats; beatIndex++) {
				TGBeat beat = measure.getBeat(beatIndex);
				processor.process(timestamp, beat);

				/**
				 * voices
				 */
				int countVoices = beat.countVoices();
				for (int voiceIndex = 0; voiceIndex < countVoices; voiceIndex++) {
					TGVoice voice = beat.getVoice(voiceIndex);
					if (voice.isEmpty()) continue;

					processor.process(timestamp, voice);

					/**
					 * notes
					 */
					int countNotes = voice.countNotes();
					for (int noteIndex = 0; noteIndex < countNotes; noteIndex++) {
						TGNote note = voice.getNote(noteIndex);
						processor.process(timestamp, note);
					}

					timestamp += voice.getDuration().getTime();
				}
			}
		}
	}

	/**
	 * build measure list
	 * 
	 * @param song
	 * @param rocksmithSong
	 * @return
	 * @throws Exception
	 */
	private void processTrack(Song song, int index, double beat,
			ITrackProcessor processor) throws Exception {
		int countMeasures = 0;
		double timestamp = 0;

		/**
		 * iterate on phrases
		 */
		for (int iPhrase = 0; iPhrase < song.getPhraseCount(); iPhrase++) {
			SongPhrase phrase = song.getPhrase(iPhrase);
			for (int iMeasure = 0; iMeasure < phrase
					.getPerformanceMeasureCount(); iMeasure++, countMeasures++) {
				/**
				 * process this measure
				 */
				SongMeasure measure = phrase.getPerformanceMeasure(iMeasure);
				processor.process(countMeasures, timestamp, measure);

				/**
				 * load events
				 */
				SongMeasureTrack track = measure.getTrack(song.getTrack(index));
				processor.process(index, track);

				/**
				 * for tied notes see how to discover which note is tied
				 */

				for (SongEvent event : track.getEvents()) {
					SongMessage message = event.getMessage();
					/**
					 * message is a note
					 */
					if (message.getClass() == SongNoteOnMessage.class) {
						processor.process(event, (SongNoteOnMessage) message);
					}
					/**
					 * message is a note offset
					 */
					if (message.getClass() == SongNoteOffMessage.class) {
						processor.process(event, (SongNoteOffMessage) message);
					}
					/**
					 * message is a note offset
					 */
					if (message.getClass() == SongTieMessage.class) {
						processor.process(event, (SongTieMessage) message);
					}
					/**
					 * message is a note offset
					 */
					if (message.getClass() == SongNotationMessage.class) {
						processor.process(event, (SongNotationMessage) message);
					}
				}
				/**
				 * next timestamp based on measure size, convert with beat size
				 * in second
				 */
				timestamp += measure.getLength() / song.getResolution() * beat;
			}
		}
		/**
		 * commmit processing
		 */
		processor.commit(song.getTempo().getBPM(), timestamp);
	}
}
