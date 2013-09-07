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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.yroffin.rocksmith.model.IMeasure;
import org.yroffin.rocksmith.model.INote;

public class MeasureEntity implements IMeasure {

	private final double time;
	private int measure;
	private final int numerator;
	private final String markerName;

	/**
	 * internal measures
	 */
	private static List<IMeasure> measures = new ArrayList<IMeasure>();

	/**
	 * factory
	 * 
	 * @param time
	 * @param numerator
	 * @param markerName
	 * @return
	 */
	public static IMeasure factory(double bpm, int numerator, String markerName) {
		MeasureEntity entity = new MeasureEntity((60. / bpm) * measures.size(),
				bpm, numerator, markerName);
		entity.setMeasureId(measures.size());
		measures.add(entity);
		return entity;
	}

	/**
	 * notes vector
	 */
	private final Map<Double, List<INote>> notes = new TreeMap<Double, List<INote>>();
	private final double bpm;

	/**
	 * constructor
	 * 
	 * @param time
	 * @param bpm
	 * @param numerator
	 * @param markerName
	 */
	private MeasureEntity(double time, double bpm, int numerator,
			String markerName) {
		this.time = time;
		this.numerator = numerator;
		this.markerName = markerName;
		this.bpm = bpm;
	}

	/**
	 * add notes
	 */
	public INote add(double timestamp, INote note) {
		List<INote> insertPosition = notes.get(timestamp);
		if (insertPosition == null) {
			insertPosition = notes.put(timestamp, new ArrayList<INote>());
		}
		notes.get(timestamp).add(note);
		return note;
	}

	public Map<Double, List<INote>> getNotes() {
		return notes;
	}

	private void setMeasureId(int measure) {
		this.measure = measure;
	}

	public int getMeasureId() {
		return measure;
	}

	public double getTime() {
		return time;
	}

	public int getNumerator() {
		return numerator;
	}

	public boolean hasMarker() {
		return markerName != null;
	}

	public String getMarkerName() {
		return markerName;
	}

	public double getBpm() {
		return bpm;
	}

	@Override
	public String toString() {
		return "MeasureEntity [time=" + time + ", measure=" + measure
				+ ", numerator=" + numerator + ", markerName=" + markerName
				+ ", notes=" + notes + "]";
	}
}
