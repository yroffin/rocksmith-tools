/*
 * Created on Mar 20, 2005
 */
package test.tools;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiFileFormat;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import common.MidiEventComparator;

import test.suite.MidiAdaptorTest;

/**
 * @author Chris
 */
public class MidiTools extends MidiAdaptorTest
{

    /**
     * @param output
     */
    public static void filterOptionalMeta(List<?> output)
    {
        for (Iterator<?> it = output.iterator(); it.hasNext();)
        {
            MidiEvent me = (MidiEvent) it.next();
            MidiMessage mm = me.getMessage();
            if (mm.getStatus() == MetaMessage.META)
            {
                MetaMessage meta = (MetaMessage) mm;
                int type = meta.getType();

                if (type == 0x03)
                {
                    it.remove();
                } else if (type == 0x2F)
                {
                    it.remove();
                } else if (type == 0x58)
                {
                    // Another JavaSound glitch. This time, the trail bytes of
                    // a time signature message are getting bits of the usq
                    // timing... sigh.
                    // Manually set them to 0818.
                    byte[] data = meta.getData();
                    byte[] newData = new byte[4];

                    newData[0] = data[data.length - 4];
                    newData[1] = data[data.length - 3];
                    newData[2] = 0x18;
                    newData[3] = 0x08;
                    try
                    {
                        meta.setMessage(0x58, newData, 4);
                    } catch (InvalidMidiDataException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @param m1
     * @param m2
     */
    public static void compareEvents(MidiEvent m1, MidiEvent m2)
    {
        String s1 = MidiTools.stringize(m1);
        String s2 = MidiTools.stringize(m2);
        assertEquals(s1, s2);
    }

    /**
     * @param m1
     * @param m2
     */
    public static void compareMessages(MidiMessage m1, MidiMessage m2)
    {
        String s1 = MidiTools.stringize(m1);
        String s2 = MidiTools.stringize(m2);
        assertEquals(s1, s2);
    }

    /**
     * @param status
     * 
     */
    public static void hexize(int status, StringBuffer sb)
    {
        hexbit((status >> 4) & 0x0F, sb);
        hexbit((status) & 0x0F, sb);
    }

    /**
     * @param i
     * @param sb
     */
    public static void hexbit(int i, StringBuffer sb)
    {
        String hex = "0123456789ABCDEF";
        sb.append(hex.charAt(i));
    }

    /**
     * @param output
     */
    public static void prioritizeByChannel(List<?> output)
    {
        Collections.sort(output, new Comparator<Object>()
        {
            public int compare(Object o1, Object o2)
            {
                MidiEvent m1 = (MidiEvent) o1;
                MidiEvent m2 = (MidiEvent) o2;
                if (m1.getTick() < m2.getTick())
                    return -1;
                if (m1.getTick() > m2.getTick())
                    return 1;

                MidiMessage mm1 = m1.getMessage();
                MidiMessage mm2 = m2.getMessage();

                int s1 = mm1.getStatus();
                int s2 = mm2.getStatus();

                if (s1 >= 0x00F0)
                    return 0;
                if (s2 >= 0x00F0)
                    return 0;

                int c1 = s1 & 0x0F;
                int c2 = s2 & 0x0F;

                if (c1 != c2)
                    return c1 - c2;

                int x1 = s1 & 0x00f0;
                int x2 = s2 & 0x00f0;

                if ((x1 == ShortMessage.NOTE_ON)
                        && (x2 == ShortMessage.NOTE_ON))
                {
                    ShortMessage sm1 = (ShortMessage) mm1;
                    ShortMessage sm2 = (ShortMessage) mm2;
                    return (sm1.getData1() - sm2.getData1());
                }

                return 0;
            }

        });
    }

    public static String stringize(MidiEvent e)
    {
        String s1 = Long.toString(e.getTick());
        return s1 + ":" + stringize(e.getMessage());
    }

    /**
     * @param m
     * @return a string for a MidiMessage
     */
    public static String stringize(MidiMessage m)
    {
        StringBuffer sb = new StringBuffer();
        int status = m.getStatus();
        if (m instanceof ShortMessage)
        {
            ShortMessage sm = (ShortMessage) m;
            hexize(status, sb);
            hexize(sm.getData1(), sb);
            hexize(sm.getData2(), sb);
        } else
        {
            int l = m.getLength();
            byte[] data = m.getMessage();
            for (int i = 0; i < l; i++)
            {
                hexize(data[i], sb);
            }
        }

        return sb.toString();
    }

    /**
     * @param output
     * @param sourcePPQ
     * @param destPPQ
     */
    public static void remapTicks(List<?> output, int sourcePPQ, int destPPQ)
    {
        for (Iterator<?> it = output.iterator(); it.hasNext();)
        {
            MidiEvent event = (MidiEvent) it.next();
            long t1 = event.getTick();
            t1 *= destPPQ;
            t1 /= sourcePPQ;
            event.setTick(t1);
        }
    }

    public static void remapNoteOff(List<MidiEvent> output)
    {
        int r100 = 0;
        int r101 = 0;

        List<MidiEvent> results = new LinkedList<MidiEvent>();
        for (Iterator<MidiEvent> it = output.iterator(); it.hasNext();)
        {
            MidiEvent me = (MidiEvent) it.next();
            MidiMessage mm = me.getMessage();
            int status = mm.getStatus();
            int command = status & 0x00F0;
            int channel = status & 0x0F;
            if (command == ShortMessage.NOTE_OFF)
            {
                ShortMessage s1 = (ShortMessage) mm;
                ShortMessage s2 = new ShortMessage();
                try
                {
                    s2.setMessage(ShortMessage.NOTE_ON + channel,
                            s1.getData1(), 0);
                    MidiEvent me2 = new MidiEvent(s2, me.getTick());
                    results.add(me2);
                } catch (InvalidMidiDataException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (command == ShortMessage.PITCH_BEND)
            {
                // ignore bends
            } else if (command == ShortMessage.CONTROL_CHANGE)
            {
                ShortMessage sm = (ShortMessage) mm;
                int controller = sm.getData1();
                int value = sm.getData2();
                switch (controller)
                {
                case 100:
                    r100 = value;
                    results.add(me);
                    break;
                case 101:
                    r101 = value;
                    results.add(me);
                    break;
                case 6:
                {
                    // registered controller. If it is pitch bend sens,
                    // then override it and set it to 12 for right now
                    if ((r100 == 0) && (r101 == 0))
                    {
                        ShortMessage s2 = new ShortMessage();
                        try
                        {
                            s2.setMessage(
                                    ShortMessage.CONTROL_CHANGE + channel, 6,
                                    12);
                        } catch (InvalidMidiDataException e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        MidiEvent me2 = new MidiEvent(s2, me.getTick());
                        results.add(me2);
                    } else
                    {
                        results.add(me);
                    }
                }
                    break;
                default:
                    results.add(me);
                    break;
                }
            } else
            {
                results.add(me);
            }
        }

        output.clear();
        output.addAll(results);
    }

    /**
     * Load a MIDI file (cheap and nasty) TODO filter out MIDI meta events
     * 
     * @param eventList
     *            a list to fill out with the sorted list of MIDI events
     * @return the pulses per quarter note set in this file
     * @throws IOException
     * @throws InvalidMidiDataException
     */
    public static int load(String midiFile, List<MidiEvent> eventList)
            throws InvalidMidiDataException, IOException
    {
        File file = new File(midiFile);
        MidiFileFormat mff = MidiSystem.getMidiFileFormat(file);
        int ppq = mff.getResolution();

        Sequence seq = MidiSystem.getSequence(file);
        Track[] tracks = seq.getTracks();

        for (int t = 0; t < tracks.length; t++)
        {
            Track track = tracks[t];
            int size = track.size();
            for (int j = 0; j < size; j++)
            {
                MidiEvent event = track.get(j);
                eventList.add(event);
            }
        }
        Collections.sort(eventList, new MidiEventComparator());
        return ppq;
    }

    /**
     * @param original
     * @param effectsChannel
     * @param mainChannel
     */
    public static void remapChannel(List<MidiEvent> original, int effectsChannel,
            int mainChannel)
    {
        List<MidiEvent> results = new LinkedList<MidiEvent>();
        for (Iterator<MidiEvent> it = original.iterator(); it.hasNext();)
        {
            MidiEvent me = (MidiEvent) it.next();
            MidiMessage mm = me.getMessage();
            int status = mm.getStatus();
            int command = status & 0x00F0;
            int channel = status & 0x000F;

            if ((channel != effectsChannel - 1) || (command == 0x00F0))
            {
                results.add(me);
            } else
            {
                // it's a real command, on the effects channel
                switch (command)
                {
                case ShortMessage.NOTE_ON:
                case ShortMessage.NOTE_OFF:
                {
                    ShortMessage source = (ShortMessage) mm;
                    ShortMessage replacement = new ShortMessage();
                    try
                    {
                        replacement.setMessage(command + (mainChannel - 1),
                                source.getData1(), source.getData2());
                        MidiEvent me2 = new MidiEvent(replacement, me.getTick());
                        results.add(me2);
                    } catch (InvalidMidiDataException e)
                    {
                        e.printStackTrace();
                    }

                }
                    break;
                default:
                    break;
                }
            }
        }

        original.clear();
        original.addAll(results);
    }

    /**
     * @param original
     * @param eventRemap
     * @throws Exception
     */
    public static void remapEvents(List<MidiEvent> original, String[] eventRemap) throws Exception
    {
        List<MidiEvent> results = new LinkedList<MidiEvent>();

        int nextEventRemap = 0;
        for (Iterator<MidiEvent> it = original.iterator(); it.hasNext();)
        {
            MidiEvent me = (MidiEvent) it.next();
            String thisEvent = stringize(me);
            if ((nextEventRemap < eventRemap.length)
                    && (thisEvent.equals(eventRemap[nextEventRemap])))
            {
                // it's a match
                String replacement=eventRemap[nextEventRemap+1];
                nextEventRemap+=2;
                
                if(replacement.equals(""))
                {
                }
                else
                {
                    // it is time to build an event. Note the event looks like
                    // time:bytes (and I think we can safely assume it is
                    // a shortmessage)
                    
                    int colon=replacement.indexOf(":");
                    int time=Integer.parseInt(replacement.substring(0,colon),10);
                    int status=Integer.parseInt(replacement.substring(colon+1,colon+3),16);
                    int data1=Integer.parseInt(replacement.substring(colon+3,colon+5),16);
                    int data2=Integer.parseInt(replacement.substring(colon+5,colon+7),16);
                    
                    ShortMessage sm=new ShortMessage();
                    try
                    {
                        sm.setMessage(status,data1,data2);
                        MidiEvent me2=new MidiEvent(sm,time);
                        results.add(me2);
                    }
                    catch (InvalidMidiDataException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                }
            }
            else
            {
                results.add(me);
            }
        }
        
        if(nextEventRemap<eventRemap.length)
        {
            throw new Exception("No match found to event "+eventRemap[nextEventRemap]);
        }

        original.clear();
        original.addAll(results);
    }

}