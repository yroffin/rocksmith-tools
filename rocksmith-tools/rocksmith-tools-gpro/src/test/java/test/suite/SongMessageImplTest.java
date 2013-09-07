/*
 * Created on Mar 21, 2005
 */
package test.suite;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import dguitar.adaptors.song.event.SongNoteOnMessage;

/**
 * @author crnash
 */
public class SongMessageImplTest extends TestCase
{
    public void testHashCode()
    {
        Set<SongNoteOnMessage> set=new HashSet<SongNoteOnMessage>();
        
        set.add(new SongNoteOnMessage(0,0,0,0));
        set.add(new SongNoteOnMessage(0,0,0,0));
        assertEquals(1,set.size());
    }
}
