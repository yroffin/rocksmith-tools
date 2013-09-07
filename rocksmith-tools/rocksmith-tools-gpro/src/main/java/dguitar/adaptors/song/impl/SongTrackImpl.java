/*
 * Created on Mar 2, 2005
 */
package dguitar.adaptors.song.impl;

import java.util.Arrays;

import dguitar.adaptors.song.SongDevice;
import dguitar.adaptors.song.SongTrack;

/**
 * Implementation of SongTrack
 * 
 * @author crnash
 */
public class SongTrackImpl implements SongTrack {
	int index;
	int virtualTrackCount;

	SongDevice primaryDevice;
	SongDevice secondaryDevice;

	int bendSensitivity;
	int chorus;
	int pan;
	int phaser;
	int program;
	int reverb;
	int tremolo;
	int volume;
	private final int[] stringTuning;

	public int[] getStringTuning() {
		return stringTuning;
	}

	public SongTrackImpl(int index, int virtualTracks, int[] stringTuning) {
		this.index = index;
		this.virtualTrackCount = virtualTracks;
		this.stringTuning = stringTuning;
	}

	/**
	 * @return Returns the primaryDevice.
	 */
	public SongDevice getPrimaryDevice() {
		return primaryDevice;
	}

	/**
	 * @param primaryDevice
	 *            The primaryDevice to set.
	 */
	public void setPrimaryDevice(SongDevice primaryDevice) {
		this.primaryDevice = primaryDevice;
	}

	/**
	 * @return Returns the secondaryDevice.
	 */
	public SongDevice getSecondaryDevice() {
		return secondaryDevice;
	}

	/**
	 * @param secondaryDevice
	 *            The secondaryDevice to set.
	 */
	public void setSecondaryDevice(SongDevice secondaryDevice) {
		this.secondaryDevice = secondaryDevice;
	}

	private static int clip(int value, int max) {
		if (value < 0) return 0;
		if (value > max) return max;
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Song.SongTrack#setVolume(int)
	 */

	/**
	 * @return Returns the virtualTracks.
	 */
	public int getVirtualTrackCount() {
		return virtualTrackCount;
	}

	/**
	 * @return Returns the chorus.
	 */
	public int getChorus() {
		return chorus;
	}

	/**
	 * @param chorus
	 *            The chorus to set.
	 */
	public void setChorus(int chorus) {
		this.chorus = clip(chorus, 127);
	}

	/**
	 * @return Returns the pan.
	 */
	public int getPan() {
		return pan;
	}

	/**
	 * @param pan
	 *            The pan to set.
	 */
	public void setPan(int pan) {
		this.pan = clip(pan, 127);
	}

	/**
	 * @return Returns the phaser.
	 */
	public int getPhaser() {
		return phaser;
	}

	/**
	 * @param phaser
	 *            The phaser to set.
	 */
	public void setPhaser(int phaser) {
		this.phaser = clip(phaser, 127);
	}

	/**
	 * @return Returns the reverb.
	 */
	public int getReverb() {
		return reverb;
	}

	/**
	 * @param reverb
	 *            The reverb to set.
	 */
	public void setReverb(int reverb) {
		this.reverb = clip(reverb, 127);
	}

	/**
	 * @return Returns the tremolo.
	 */
	public int getTremolo() {
		return tremolo;
	}

	/**
	 * @param tremolo
	 *            The tremolo to set.
	 */
	public void setTremolo(int tremolo) {
		this.tremolo = clip(tremolo, 127);
	}

	/**
	 * @return Returns the volume.
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            The volume to set.
	 */
	public void setVolume(int volume) {
		this.volume = clip(volume, 127);
	}

	/**
	 * @return Returns the index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return Returns the bendSensitivity.
	 */
	public int getBendSensitivity() {
		return bendSensitivity;
	}

	/**
	 * @param bendSensitivity
	 *            The bendSensitivity to set.
	 */
	public void setBendSensitivity(int bendSensitivity) {
		this.bendSensitivity = clip(bendSensitivity, 127);
	}

	/**
	 * @return Returns the program.
	 */
	public int getProgram() {
		return program;
	}

	/**
	 * @param program
	 *            The program to set.
	 */
	public void setProgram(int program) {
		this.program = clip(program, 127);
	}

	@Override
	public String toString() {
		return "SongTrackImpl [index=" + index + ", virtualTrackCount="
				+ virtualTrackCount + ", primaryDevice=" + primaryDevice
				+ ", secondaryDevice=" + secondaryDevice + ", bendSensitivity="
				+ bendSensitivity + ", chorus=" + chorus + ", pan=" + pan
				+ ", phaser=" + phaser + ", program=" + program + ", reverb="
				+ reverb + ", tremolo=" + tremolo + ", volume=" + volume
				+ ", stringTuning=" + Arrays.toString(stringTuning) + "]";
	}
}
