/*
 * Created on Mar 27, 2005
 */
package test.suite;

import junit.framework.TestCase;
import dguitar.adaptors.song.Tempo;

/**
 * @author Chris
 */
public class TempoTestCase extends TestCase
{
    Tempo tempo;
    public void setUp()
    {
        tempo=new Tempo();
    }
    
    public void testGetBeat()
    {
        assertEquals(4,tempo.getBeat());
    }

    public void testGetBPM()
    {
        assertEquals(120.0,tempo.getBPM(),0.01);
    }

    public void testGetUSQ()
    {
        assertEquals(500000.0,tempo.getUSQ(),0.01);
    }

    public void testSetBeat()
    {
        tempo.setBeat(8);
        assertEquals(8,tempo.getBeat());
        assertEquals(120.0,tempo.getBPM(),0.01);
        assertEquals(1000000.0,tempo.getUSQ(),0.01);
    }

    public void testSetBPM()
    {
        tempo.setBPM(100.0);
        assertEquals(4,tempo.getBeat());
        assertEquals(100.0,tempo.getBPM(),0.01);
        assertEquals(600000.0,tempo.getUSQ(),0.01);
    }

    public void testSetUSQ()
    {
        tempo.setUSQ(600000.0);
        assertEquals(4,tempo.getBeat());
        assertEquals(100.0,tempo.getBPM(),0.01);
        assertEquals(600000.0,tempo.getUSQ(),0.01);
    }
}
