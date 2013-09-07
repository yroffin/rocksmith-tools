/*
 * Created on Mar 23, 2005
 */
package test.suite;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import test.songs.MidiSongDefinition;
import test.songs.SongArchive;
import dguitar.adaptors.guitarPro.GPAdaptor;
import dguitar.adaptors.song.Song;
import dguitar.adaptors.song.Tempo;
import dguitar.codecs.CodecFormatException;
import dguitar.codecs.guitarPro.GPFormatException;
import dguitar.codecs.guitarPro.GPInputStream;
import dguitar.codecs.guitarPro.GPSong;
import dguitar.codecs.midi.MidiOutputStream;
import dguitar.players.sound.MasterPlayer;
import dguitar.players.sound.Performance;
import dguitar.players.sound.midi.MidiFiler;

/**
 * @author crnash
 */
public class GPTest extends MidiTestCase {
	public void testFourHands() throws Exception {
		MidiSongDefinition sd = SongArchive.fourhandsSongDefinition();
		_testGPWithPlayer(sd, 540540);
	}

	public void _testGP(MidiSongDefinition sd) throws Exception {
		MidiSongDefinition testFile = SongArchive.testFileSongDefinition();
		try {
			GPInputStream gpis = new GPInputStream(sd.getGpFileName());
			GPSong gpsong = gpis.readObject();
			gpis.close();

			MidiOutputStream mos = new MidiOutputStream(new FileOutputStream(
					testFile.getMidiFileName()));
			mos.write(gpsong);
			mos.close();

			compareMIDIFiles(sd.getMidiFileName(), testFile.getMidiFileName(),
					sd.getChannels(), sd.getEventRemap());
		} catch (FileNotFoundException e) {
			fail("file not found exception");
		} catch (GPFormatException e) {
			fail("gp format exception");
		} catch (IOException e) {
			fail("ioexception");
		} catch (CodecFormatException e) {
			fail("codec format exception");
		} catch (InvalidMidiDataException e) {
			fail("invalid midi data exception");
		}
	}

	public void _testGPWithPlayer(MidiSongDefinition sd, int usq)
			throws Exception {
		MidiSongDefinition testFile = SongArchive.testFileSongDefinition();
		try {
			GPInputStream gpis = new GPInputStream(sd.getGpFileName());
			GPSong gpsong = gpis.readObject();
			gpis.close();

			int tempoGPSong = gpsong.getTempo();
			assertEquals((60 * 1000 * 1000 / usq), tempoGPSong);

			Song song = GPAdaptor.makeSong(gpsong);

			Tempo tempoSong = song.getTempo();
			assertEquals(usq, (int) tempoSong.getUSQ());

			MasterPlayer player = new MasterPlayer();
			player.setSoundPlayer(new MidiFiler(testFile.getMidiFileName()));
			Performance performance = player.arrange(song, null);

			Tempo tempoPerformance = performance.getTempo();
			assertEquals(usq, (int) tempoPerformance.getUSQ());

			// a performance is really a sequence. So make sure there
			// is a tempo event (meta 0x51) on track 0.

			// make sure as well there is exactly ONE tempo event at timestamp 0
			Sequence sequence = (Sequence) performance;
			Track[] midiTracks = sequence.getTracks();
			Track tempoMap = midiTracks[0];

			Tempo tempoInMIDI = new Tempo();
			for (int i = 0; i < tempoMap.size(); i++) {
				MidiEvent me = tempoMap.get(i);
				long tick = me.getTick();
				if (tick > 0) break;
				MidiMessage mm = me.getMessage();
				if (mm.getStatus() == MetaMessage.META) {
					MetaMessage meta = (MetaMessage) mm;
					if (meta.getType() == 0x51) {
						byte[] data = meta.getData();
						tempoInMIDI.setUSQ(((data[0] & 0x00FF) << 16)
								| ((data[1] & 0x00FF) << 8)
								| ((data[2] & 0x00FF)));
						break;
					}
				}
			}
			assertEquals(usq, (int) tempoInMIDI.getUSQ());

			MidiOutputStream mos = new MidiOutputStream(new FileOutputStream(
					testFile.getMidiFileName()));
			mos.write(performance);
			mos.close();

			compareMIDIFiles(sd.getMidiFileName(), testFile.getMidiFileName(),
					sd.getChannels(), sd.getEventRemap());
		} catch (FileNotFoundException e) {
			fail("file not found exception");
		} catch (GPFormatException e) {
			fail("gp format exception");
		} catch (IOException e) {
			fail("ioexception");
		} catch (CodecFormatException e) {
			fail("codec format exception");
		} catch (InvalidMidiDataException e) {
			fail("invalid midi data exception");
		}
	}
}
