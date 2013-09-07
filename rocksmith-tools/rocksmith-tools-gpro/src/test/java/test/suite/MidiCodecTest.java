/*
 * Created on Mar 21, 2005
 */
package test.suite;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import test.songs.MidiSongDefinition;
import test.songs.SongArchive;
import dguitar.adaptors.guitarPro.GPAdaptor;
import dguitar.adaptors.song.Song;
import dguitar.codecs.CodecFormatException;
import dguitar.codecs.guitarPro.GPFormatException;
import dguitar.codecs.guitarPro.GPInputStream;
import dguitar.codecs.guitarPro.GPSong;
import dguitar.codecs.midi.MidiOutputStream;
import dguitar.players.sound.MasterPlayer;
import dguitar.players.sound.Performance;
import dguitar.players.sound.midi.MidiFiler;
import dguitar.players.sound.midi.MidiPlayer;

/**
 * @author crnash
 */
public class MidiCodecTest extends MidiTestCase
{

    /**
     * Perform the same test using the Guitar Pro source. Call using the
     * midi output codec instead. Use a live player to see if the codec
     * can write a live object
     * @throws Exception
     */
    public void testAguaGPWithLivePlayer() throws Exception
    {
            MidiSongDefinition testFile=SongArchive.testFileSongDefinition();            
            MidiSongDefinition sd = SongArchive.aguaSongDefinition();
    
            try
            {
                GPInputStream gpis = new GPInputStream(sd.getGpFileName());
                GPSong gpsong=(GPSong) gpis.readObject();
                gpis.close();
                
                Song song=GPAdaptor.makeSong(gpsong);
                
                MasterPlayer player=new MasterPlayer();
                player.setSoundPlayer(new MidiPlayer());
                Performance performance=player.arrange(song,null);
                
                MidiOutputStream mos=new MidiOutputStream(new FileOutputStream(testFile.getMidiFileName()));
                mos.write(performance);
                mos.close();
                
                compareMIDIFiles(sd.getMidiFileName(),testFile.getMidiFileName(),
                        		 sd.getChannels(),sd.getEventRemap());
            }
            catch (FileNotFoundException e)
            {
                fail("file not found exception");
            }
            catch (GPFormatException e)
            {
                fail("gp format exception");
            }
            catch (IOException e)
            {
                fail("ioexception");
            }
            catch (CodecFormatException e)
            {
                fail("codec format exception");
            }
            catch (InvalidMidiDataException e)
            {
               fail("invalid midi data exception");
            }
    }

    /**
     * Perform the same test using the Guitar Pro source. Call using the
     * midi output codec instead.
     * @throws Exception
     */
    public void testAguaGPWithPlayer() throws Exception
    {
            MidiSongDefinition testFile=SongArchive.testFileSongDefinition();            
            MidiSongDefinition sd = SongArchive.aguaSongDefinition();
    
            try
            {
                GPInputStream gpis = new GPInputStream(sd.getGpFileName());
                GPSong gpsong=(GPSong) gpis.readObject();
                gpis.close();
                
                Song song=GPAdaptor.makeSong(gpsong);
                
                MasterPlayer player=new MasterPlayer();
                player.setSoundPlayer(new MidiFiler(testFile.getMidiFileName()));
                Performance performance=player.arrange(song,null);
                
                MidiOutputStream mos=new MidiOutputStream(new FileOutputStream(testFile.getMidiFileName()));
                mos.write(performance);
                mos.close();
                
                compareMIDIFiles(sd.getMidiFileName(),testFile.getMidiFileName(),sd.getChannels(),sd.getEventRemap());
            }
            catch (FileNotFoundException e)
            {
                fail("file not found exception");
            }
            catch (GPFormatException e)
            {
                fail("gp format exception");
            }
            catch (IOException e)
            {
                fail("ioexception");
            }
            catch (CodecFormatException e)
            {
                fail("codec format exception");
            }
            catch (InvalidMidiDataException e)
            {
               fail("invalid midi data exception");
            }
    }

    /**
     * Perform the same test using the Guitar Pro source. Call using the
     * midi output codec instead.
     * @throws Exception
     */
    public void testAguaGP() throws Exception
    {
            MidiSongDefinition testFile=SongArchive.testFileSongDefinition();            
            MidiSongDefinition sd = SongArchive.aguaSongDefinition();
    
            try
            {
                GPInputStream gpis = new GPInputStream(sd.getGpFileName());
                GPSong gpsong=(GPSong) gpis.readObject();
                gpis.close();
                
                MidiOutputStream mos=new MidiOutputStream(new FileOutputStream(testFile.getMidiFileName()));
                mos.write(gpsong);
                mos.close();
                
                compareMIDIFiles(sd.getMidiFileName(),testFile.getMidiFileName(),sd.getChannels(),sd.getEventRemap());
            }
            catch (FileNotFoundException e)
            {
                fail("file not found exception");
            }
            catch (GPFormatException e)
            {
                fail("gp format exception");
            }
            catch (IOException e)
            {
                fail("ioexception");
            }
            catch (CodecFormatException e)
            {
                fail("codec format exception");
            }
            catch (InvalidMidiDataException e)
            {
               fail("invalid midi data exception");
            }
    }

}
