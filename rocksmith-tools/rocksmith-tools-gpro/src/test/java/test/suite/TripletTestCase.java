/*
 * Created on Mar 27, 2005
 */
package test.suite;

import java.io.IOException;

import junit.framework.TestCase;
import test.songs.SongArchive;
import test.songs.SongDefinition;
import test.tools.SingleMeasureArrangement;
import dguitar.adaptors.guitarPro.GPAdaptor;
import dguitar.adaptors.song.Song;
import dguitar.codecs.guitarPro.GPFormatException;
import dguitar.codecs.guitarPro.GPInputStream;
import dguitar.codecs.guitarPro.GPSong;
import dguitar.players.sound.MasterPlayer;
import dguitar.players.sound.PerformanceEvent;
import dguitar.players.sound.PerformanceEventListener;
import dguitar.players.sound.PerformanceTimerEvent;
import dguitar.players.sound.PerformanceTimerListener;
import dguitar.players.sound.midi.MidiPlayer;

/**
 * @author Chris
 */
public class TripletTestCase extends TestCase {
	/**
	 * Test that triplets are adapted and played correctly.
	 * 
	 * @throws IOException
	 * @throws GPFormatException
	 */
	public void testTriplet() throws GPFormatException, IOException {
		SongDefinition sd = SongArchive.somethingSongDefinition();

		Song song = loadGPToSong(sd.getGpFileName());
		MasterPlayer player = new MasterPlayer();
		player.setSoundPlayer(new MidiPlayer());
		player.setTimerFrequency(4);
		player.enableNoteEvents(true); // deliver all note events to all
										// Listeners

		player.addTimerListener(new PerformanceTimerListener() {
			public void onTimer(PerformanceTimerEvent timerEvent) {
			}
		});

		player.addEventListener(new PerformanceEventListener() {
			public void onEvent(PerformanceEvent event) {
			}
		});

		player.arrange(song, new SingleMeasureArrangement(0));

		// Tell the master player to start playing, which will start the
		// performance, all the clocks, and begin sending events to listeners.
		player.start();

		// wait until the player is finished. You can call player.stop() if
		// you want to stop early. You may even be able to stop and restart.
		player.waitForCompletion();

		// And then close it. You must close the master player when you have
		// finished with it, and throw it away. Later I will make sure you
		// can just start one at the start of the application and reuse it
		// again and again.
		player.close();
	}

	/**
	 * Attempt to load a GP file as a Song.
	 * 
	 * @param fileName
	 * @return a Song
	 * @throws GPFormatException
	 * @throws IOException
	 */
	public static Song loadGPToSong(String fileName) throws GPFormatException,
			IOException {
		GPInputStream gpis = new GPInputStream(fileName);
		GPSong piece = (GPSong) gpis.readObject();
		gpis.close();
		Song song = GPAdaptor.makeSong(piece);
		return song;
	}
}
