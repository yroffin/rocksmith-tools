/*
 * Created on Mar 19, 2005
 */
package test.suite;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import test.songs.MidiSongDefinition;
import test.songs.SongArchive;
import dguitar.adaptors.midi.MidiAdaptor;
import dguitar.adaptors.song.Song;
import dguitar.players.sound.Arrangement;
import dguitar.players.sound.MasterPlayer;
import dguitar.players.sound.midi.MidiFiler;

/**
 * @author Chris
 */
public class MidiAdaptorTest extends MidiTestCase
{  
    /**
     * Take one of the songs in the test suite that has a MIDI file.
     * Load it using the MIDI adaptor, then write it back out using
     * the MIDI filer. Remove optional metadata, standardize all MIDI
     * events, and priooritize events by channel. The two resulting
     * streams should be equal after adjusting to the same resolution.
     * @throws Exception
     */
    public void testAguaMIDI() throws Exception
    {
            MidiSongDefinition testFile=SongArchive.testFileSongDefinition();
            
            MidiSongDefinition sd = SongArchive.aguaSongDefinition();
            Song song=null;
            try
            {
                song = MidiAdaptor.makeSong(sd.getMidiFileName());
            }
            catch (InvalidMidiDataException e1)
            {
                fail("invalid midi data exception");
            }
            catch (IOException e1)
            {
                fail("ioexception");
            }
            
            MasterPlayer player = new MasterPlayer();
            player.setSoundPlayer(new MidiFiler(testFile.getMidiFileName()));
            Arrangement arrangement = null;
            player.arrange(song, arrangement);
            player.start();

            // since MidiFiler is not a realtime player, that's all you need.
            player.close();
            
            try
            {
                compareMIDIFiles(sd.getMidiFileName(),testFile.getMidiFileName(),null,null);
            }
            catch (InvalidMidiDataException e)
            {
                fail("invalid midi data exception");
            }
            catch (IOException e)
            {
                fail("io exception");
            }
    }

}