/*
 * Created on Mar 16, 2005
 */
package dguitar.adaptors.song;

import java.util.Collection;

/**
 * A virtual track contains all the events on a specific slice of a parent
 * SongMeasureTrack. For example, the note events on a single guitar string arer
 * collated on a single virtual track.
 * 
 * @author Chris
 */
public interface SongVirtualTrack {

	/**
	 * @return the MeasureTrack
	 */
	SongMeasureTrack getMeasureTrack();

	/**
	 * @return the index of the Virtual Track
	 */
	int getIndex();

	public void addEvent(SongEvent event);

	public void addEvents(Collection<SongEvent> events);

	public Collection<SongEvent> getEvents();
}
