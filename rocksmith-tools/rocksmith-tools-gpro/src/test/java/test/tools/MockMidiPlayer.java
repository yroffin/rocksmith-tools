/*
 * Created on Mar 18, 2005
 */
package test.tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import dguitar.adaptors.song.Tempo;
import dguitar.players.sound.EventSoundPlayer;
import dguitar.players.sound.Performance;
import dguitar.players.sound.PerformanceEventListener;
import dguitar.players.sound.PerformanceTimerListener;
import dguitar.players.sound.RealtimeSoundPlayer;
import dguitar.players.sound.SoundPlayer;
import dguitar.players.sound.midi.MetaPerformer;
import dguitar.players.sound.midi.MidiPerformanceEventListenerProxy;
import dguitar.players.sound.midi.MidiPerformanceTimerListenerProxy;
import dguitar.players.sound.midi.PerformanceLive;

/**
 * @author crnash
 */
public class MockMidiPlayer implements SoundPlayer, RealtimeSoundPlayer, EventSoundPlayer, Runnable
{
    private String className=MockMidiPlayer.class.toString();
    private Logger logger=Logger.getLogger(className);

    PerformanceLive live;
    List<MetaPerformer> listeners=new LinkedList<MetaPerformer>();
    
    Thread playerThread=new Thread(this);
    private int         timerFrequency;
    private boolean noteEventsEnabled;

    public Performance createPerformance(int tracks, Tempo tempo, int resolution)
    {
        try
        {
            PerformanceLive aLive =new PerformanceLive(tracks,tempo,resolution);
            aLive.enableNoteEvents(noteEventsEnabled);
            aLive.setTimerFrequency(timerFrequency);
            return aLive;
        }
        catch (InvalidMidiDataException e)
        {
            logger.severe("Could not create a performance object");
            return null;
        }
    }
    
    /**
     * 
     */
    public void start()
    {
        for(Iterator<MetaPerformer> it=listeners.iterator();it.hasNext();)
        {
            MetaPerformer mp=(MetaPerformer)it.next();
            mp.setContainer(live);
        }       
        playerThread.start();
    }

    /**
     * 
     */
    public void stop()
    {
        playerThread.interrupt();
    }

    /**
     * 
     */
    public void waitForCompletion()
    {
        while(playerThread.isAlive())
        {
            try
            {
                playerThread.join();
            }
            catch (InterruptedException e)
            {
            }
        }
    }

    /**
     * @param performance
     */
    public void setPerformance(Performance performance)
    {
        live=(PerformanceLive)performance;
    }

    /**
     * 
     */
    public void close()
    {
    }

    /* (non-Javadoc)
     * @see players.sound.SoundPlayer#addTimerListener(players.sound.PerformanceTimerListener)
     */
    public void addTimerListener(PerformanceTimerListener listener)
    {
        MidiPerformanceTimerListenerProxy proxy=new MidiPerformanceTimerListenerProxy(listener);
        
        listeners.add(proxy);
    }

    /* (non-Javadoc)
     * @see players.sound.SoundPlayer#addEventListener(players.sound.PerformanceEventListener)
     */
    public void addEventListener(PerformanceEventListener listener)
    {
        MidiPerformanceEventListenerProxy proxy=new MidiPerformanceEventListenerProxy(listener);
        
        listeners.add(proxy);
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run()
    {
        // The runner just pipes all the meta events in the PerformanceLive
        // object through all the meta event listeners
        List<MidiEvent> metaEvents=new LinkedList<MidiEvent>();
        
        //OLDSequence sequence=(Sequence)live;
        Sequence sequence = live;
        
        Track[] midiTracks=sequence.getTracks();
        for(int t=0;t<midiTracks.length;t++)
        {
            Track midiTrack=midiTracks[t];
            int size=midiTrack.size();
            for(int i=0;i<size;i++)
            {
                MidiEvent event=midiTrack.get(i);
                MidiMessage message=event.getMessage();
                if(message.getStatus()==MetaMessage.META)
                {
                    MetaMessage mm=(MetaMessage)message;
                    if(mm.getType()==0x01)
                    {
                        metaEvents.add(event);
                    }
                }
            }
        }
        
        // sort the meta events into time order
        Collections.sort(metaEvents,new Comparator<Object>()
                {

                    public int compare(Object o1, Object o2)
                    {
                        MidiEvent e1=(MidiEvent)o1;
                        MidiEvent e2=(MidiEvent)o2;
                        long t1=e1.getTick();
                        long t2=e2.getTick();
                        if(t1<t2) return -1;
                        if(t1>t2) return 1;
                        return 0;
                    }
                }
        );
        
        // deliver the meta events to all the meta event listeners
        for(Iterator<MidiEvent> it=metaEvents.iterator();it.hasNext();)
        {
            MidiEvent event=(MidiEvent)it.next();
            MetaMessage message=(MetaMessage) event.getMessage();
            
            for(Iterator<MetaPerformer> l=listeners.iterator();l.hasNext();)
            {
                MetaEventListener listener=(MetaEventListener)l.next();
                listener.meta(message);
            }
        }    	
	}

    /* (non-Javadoc)
     * @see players.sound.TimerSettings#setTimerFrequency(int)
     */
    public void setTimerFrequency(int ppq)
    {
        timerFrequency = ppq;
    }

    /* (non-Javadoc)
     * @see players.sound.EventSettings#enableNoteEvents(boolean)
     */
    public void enableNoteEvents(boolean enable)
    {
        noteEventsEnabled=enable;
    }
}

