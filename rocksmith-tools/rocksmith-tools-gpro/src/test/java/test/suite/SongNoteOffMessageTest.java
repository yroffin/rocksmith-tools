/*
 * Created on Mar 21, 2005
 */
package test.suite;

import junit.framework.TestCase;
import dguitar.adaptors.song.event.SongNoteOffMessage;
import dguitar.adaptors.song.event.SongNoteOnMessage;

/**
 * @author crnash
 */
public class SongNoteOffMessageTest extends TestCase
{
    /*
     * Class under test for boolean equals(Object)
     */
    public void testEqualsObject()
    {
        SongNoteOnMessage on0=new SongNoteOnMessage(0,0,0,0);
        SongNoteOffMessage off0=new SongNoteOffMessage(on0);
        
        assertEquals(true,off0.equals(off0));
    }
    public void testEqualsObject2()
    {
        SongNoteOnMessage on0=new SongNoteOnMessage(0,0,0,0);
        SongNoteOffMessage off0=new SongNoteOffMessage(on0);
        
        SongNoteOffMessage off1=new SongNoteOffMessage(on0);
        
        assertEquals(true,off0.equals(off1));
    }
    public void testEqualsObject3()
    {
        SongNoteOnMessage on0=new SongNoteOnMessage(0,0,0,0);
        SongNoteOffMessage off0=new SongNoteOffMessage(on0);
        
        SongNoteOnMessage on1=new SongNoteOnMessage(0,0,0,0);
        SongNoteOffMessage off1=new SongNoteOffMessage(on1);
        
        assertEquals(true,off0.equals(off1));
    }
    public void testEqualsObject4()
    {
        SongNoteOnMessage on0=new SongNoteOnMessage(0,0,0,0);
        SongNoteOffMessage off0=new SongNoteOffMessage(on0);
        
        SongNoteOnMessage on1=new SongNoteOnMessage(1,0,0,0);
        SongNoteOffMessage off1=new SongNoteOffMessage(on1);
        
        assertEquals(false,off0.equals(off1));
    }
    public void testEqualsObject5()
    {
        SongNoteOnMessage on0=new SongNoteOnMessage(0,0,0,0);
        SongNoteOffMessage off0=new SongNoteOffMessage(on0);
        
        SongNoteOnMessage on1=new SongNoteOnMessage(0,1,0,0);
        SongNoteOffMessage off1=new SongNoteOffMessage(on1);
        
        assertEquals(false,off0.equals(off1));
    }
    public void testEqualsObject6()
    {
        SongNoteOnMessage on0=new SongNoteOnMessage(0,0,0,0);
        SongNoteOffMessage off0=new SongNoteOffMessage(on0);
        
        SongNoteOnMessage on1=new SongNoteOnMessage(0,0,1,0);
        SongNoteOffMessage off1=new SongNoteOffMessage(on1);
        
        assertEquals(false,off0.equals(off1));
    }
    public void testEqualsObject7()
    {
        SongNoteOnMessage on0=new SongNoteOnMessage(0,0,0,0);
        SongNoteOffMessage off0=new SongNoteOffMessage(on0);
        
        SongNoteOnMessage on1=new SongNoteOnMessage(0,0,0,1);
        SongNoteOffMessage off1=new SongNoteOffMessage(on1);
        
        assertEquals(false,off0.equals(off1));
    }

}
