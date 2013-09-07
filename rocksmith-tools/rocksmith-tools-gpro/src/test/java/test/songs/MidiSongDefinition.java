/*
 * Created on Mar 19, 2005
 */
package test.songs;

/**
 * @author Chris
 */
public class MidiSongDefinition extends SongDefinition {
	String midiFileName;
	String[] eventRemap;

	public MidiSongDefinition(String gpFileName, String midiFileName) {
		super(gpFileName);
		this.midiFileName = midiFileName;
	}

	/**
	 * @return Returns the midiFileName.
	 */
	public String getMidiFileName() {
		return midiFileName;
	}

	/**
	 * @return Returns the eventRemap.
	 */
	public String[] getEventRemap() {
		return eventRemap;
	}

	/**
	 * @param eventRemap
	 *            The eventRemap to set.
	 */
	public void setEventRemap(String[] eventRemap) {
		this.eventRemap = eventRemap;
	}
}
