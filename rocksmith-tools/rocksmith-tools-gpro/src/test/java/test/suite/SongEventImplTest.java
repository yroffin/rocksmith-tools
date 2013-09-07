/*
 * Created on Mar 21, 2005
 */
package test.suite;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import dguitar.adaptors.song.SongEvent;
import dguitar.adaptors.song.SongMessage;
import dguitar.adaptors.song.event.SongEventImpl;
import dguitar.adaptors.song.event.SongNoteOnMessage;

/**
 * @author crnash
 */
public class SongEventImplTest extends TestCase
{

    public void testHashCode()
    {
        SongMessage m1=new SongNoteOnMessage(0,0,0,0);
        SongEvent se1=new SongEventImpl(1000,m1);

        SongMessage m2=new SongNoteOnMessage(0,0,0,0);
        SongEvent se2=new SongEventImpl(1000,m2);
        
        Set<SongEvent> s=new HashSet<SongEvent>();
        s.add(se1);
        s.add(se2);
        
        assertEquals(1,s.size());
    }

    public void testSongEventImpl()
    {
    }

    public void testGetTime()
    {
    }

    public void testSetTime()
    {
    }

    /*
     * Class under test for boolean equals(Object)
     */
    public void testEqualsObject()
    {
    }

    /*
     * Class under test for String toString()
     */
    public void testToString()
    {
    }

    public void testGetMessage()
    {
    }

    public void testSetMessage()
    {
    }

    public void testGetVirtualTrack()
    {
    }

    public void testSetVirtualTrack()
    {
    }

}
