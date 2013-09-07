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
public class SongNoteOnMessageTest extends TestCase
{
    SongNoteOnMessage msg=new SongNoteOnMessage(48,127,1000,5);
    
    /*
     * Class under test for boolean equals(Object)
     */
    public void testEqualsObject1()
    {
        assertEquals(true,msg.equals(msg));
    }
    public void testEqualsObject2()
    {
        assertEquals(true,msg.equals(new SongNoteOnMessage(48,127,1000,5)));
    }
    public void testEqualsObject3()
    {
        assertEquals(false,msg.equals(new SongNoteOnMessage(47,127,1000,5)));
    }
    public void testEqualsObject4()
    {
        assertEquals(false,msg.equals(new SongNoteOnMessage(48,126,1000,5)));
    }
    public void testEqualsObject5()
    {
        assertEquals(false,msg.equals(new SongNoteOnMessage(48,127,999,5)));
    }
    public void testEqualsObject6()
    {
        assertEquals(false,msg.equals(new SongNoteOnMessage(48,127,1000,4)));
    }
    public void testEqualsObject7()
    {
        assertEquals(false,msg.equals(new SongNoteOffMessage(msg)));
    }

    /*
     * Class under test for String toString()
     */
    public void testToString()
    {
        assertEquals("F5P48V127D1000",msg.toString());
    }

    public void testGetVelocity()
    {
        assertEquals(127,msg.getVelocity());
    }

    public void testSetVelocity()
    {
        msg.setVelocity(63);
        assertEquals(63,msg.getVelocity());
    }

    public void testGetDuration()
    {
        assertEquals(1000,msg.getDuration());
    }

    public void testSetDuration()
    {
        msg.setDuration(500);
        assertEquals(500,msg.getDuration());
    }

    public void testGetPitch()
    {
        assertEquals(48,msg.getPitch());
    }

    public void testSetPitch()
    {
        msg.setPitch(60);
        assertEquals(60,msg.getPitch());
    }

    public void testGetFret()
    {
        assertEquals(5,msg.getFret());
    }

    public void testSetFret()
    {
        msg.setFret(7);
        assertEquals(7,msg.getFret());
    }

}
