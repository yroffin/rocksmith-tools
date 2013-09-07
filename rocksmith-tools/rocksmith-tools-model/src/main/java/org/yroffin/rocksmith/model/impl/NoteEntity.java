/**
 *  Copyright 2012 Yannick Roffin
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.yroffin.rocksmith.model.impl;

import org.yroffin.rocksmith.model.ConvertTools;
import org.yroffin.rocksmith.model.INote;

public class NoteEntity implements INote {

	private final long time;
	private final int slap;
	private final int pluck;
	private final int bend;
	private final int fret;
	private final int hammerOn;
	private final int harmonic;
	private final int hopo;
	private final int ignore;
	private final int palmMute;
	private final int pullOff;
	private int slideTo;
	private final int string;
	private long sustain;

	private final int tremolo;
	private final boolean tied;

	public static INote factory(long time, int bend, int fret, int hammerOn,
			int harmonic, int hopo, int ignore, int palmMute, int pullOff,
			int slideTo, int string, long sustain, int tremolo, int slap,
			int pluck, boolean tied) {
		NoteEntity entity = new NoteEntity(time, bend, fret, hammerOn,
				harmonic, hopo, ignore, palmMute, pullOff, slideTo, string,
				sustain, tremolo, slap, pluck, tied);
		return entity;
	}

	private NoteEntity(long time, int bend, int fret, int hammerOn,
			int harmonic, int hopo, int ignore, int palmMute, int pullOff,
			int slideTo, int string, long sustain, int tremolo, int slap,
			int pluck, boolean tied) {
		this.slap = slap;
		this.pluck = pluck;
		this.time = time;
		this.bend = bend;
		this.fret = fret;
		this.hammerOn = hammerOn;
		this.harmonic = harmonic;
		this.hopo = hopo;
		this.ignore = ignore;
		this.palmMute = palmMute;
		this.pullOff = pullOff;
		this.slideTo = slideTo;
		this.string = string;
		this.sustain = sustain;
		this.tremolo = tremolo;
		this.tied = tied;
	}

	public boolean isTied() {
		return tied;
	}

	public void addSustain(long sustain) {
		this.sustain += sustain;
	}

	public long getTime() {
		return time;
	}

	public void setSlideTo(int value) {
		this.slideTo = value;
	}

	public int getFret() {
		return fret;
	}

	public boolean isSlide() {
		return slideTo == 1;
	}

	@Override
	public String toString() {
		return "NoteEntity [time=" + time + ", fret=" + fret + ", string="
				+ string + "]";
	}

	public StringBuilder asXml(StringBuilder xml) {
		xml.append("\n        <note time=\"" + ConvertTools.format(time)
				+ "\" bend=\"" + bend + "\" fret=\"" + fret + "\" hammerOn=\""
				+ hammerOn + "\" harmonic=\"" + harmonic + "\" hopo=\"" + hopo
				+ "\" ignore=\"" + ignore + "\" palmMute=\"" + palmMute
				+ "\" pluck=\"" + pluck + "\" pullOff=\"" + pullOff
				+ "\" slap=\"" + slap + "\" slideTo=\"" + slideTo
				+ "\" string=\"" + string + "\" sustain=\""
				+ ConvertTools.format(sustain) + "\" tremolo=\"" + tremolo
				+ "\"/>");
		return xml;
	}
}
