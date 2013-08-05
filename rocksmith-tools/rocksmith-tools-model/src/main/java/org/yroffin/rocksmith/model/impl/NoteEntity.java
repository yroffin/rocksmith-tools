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

import java.text.DecimalFormat;
import java.util.Locale;

import org.yroffin.rocksmith.model.INote;

public class NoteEntity implements INote {

	private float time;
	private final int bend;
	private final int fret;
	private final int hammerOn;
	private final int harmonic;
	private final int hopo;
	private final int ignore;
	private final int palmMute;
	private final int pullOff;
	private final int slideTo;
	private final int string;
	private final int sustain;
	private final int tremolo;

	public static INote factory(float time, int bend, int fret, int hammerOn,
			int harmonic, int hopo, int ignore, int palmMute, int pullOff,
			int slideTo, int string, int sustain, int tremolo) {
		NoteEntity entity = new NoteEntity(time, bend, fret, hammerOn,
				harmonic, hopo, ignore, palmMute, pullOff, slideTo, string,
				sustain, tremolo);
		return entity;
	}

	private NoteEntity(float time, int bend, int fret, int hammerOn,
			int harmonic, int hopo, int ignore, int palmMute, int pullOff,
			int slideTo, int string, int sustain, int tremolo) {
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
	}

	public StringBuilder asXml(StringBuilder xml) {
		DecimalFormat df = (DecimalFormat) DecimalFormat
				.getNumberInstance(Locale.ENGLISH);
		df.setMaximumFractionDigits(3);
		df.setMinimumFractionDigits(3);
		df.setDecimalSeparatorAlwaysShown(true);
		xml.append("\n<note time=\"" + df.format(time) + "\" bend=\"" + bend
				+ "\" fret=\"" + fret + "\" hammerOn=\"" + hammerOn
				+ "\" harmonic=\"" + harmonic + "\" hopo=\"" + hopo
				+ ", ignore=\"" + ignore + "\" palmMute=\"" + palmMute
				+ "\" pullOff=\"" + pullOff + "\" slideTo=\"" + slideTo
				+ "\" string=\"" + string + "\" sustain=\"" + sustain
				+ "\" tremolo=\"" + tremolo + "\" />");
		return xml;
	}
}
