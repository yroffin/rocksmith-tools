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

import org.yroffin.rocksmith.model.IChordTemplate;

public class ChordTemplateEntity implements IChordTemplate {

	private final String chordName;
	private int[] finger = new int[5];
	private int[] fret = new int[5];

	public static ChordTemplateEntity factory(String chordName, int[] finger,
			int[] fret) {
		ChordTemplateEntity entity = new ChordTemplateEntity(chordName, finger,
				fret);
		return entity;
	}

	private ChordTemplateEntity(String chordName, int[] finger, int[] fret) {
		this.chordName = chordName;
		this.finger = finger;
		this.fret = fret;
	}

	public StringBuilder asXml(StringBuilder xml) {
		xml.append("\n<chordTemplate chordName=\"" + chordName
				+ "\" finger0=\"" + finger[0] + "\" finger1=\"" + finger[1]
				+ "\" finger2=\"" + finger[2] + "\" finger3=\"" + finger[3]
				+ "\" finger4=\"" + finger[4] + "\" finger5=\"" + finger[5]
				+ "\" fret0=\"" + fret[0] + "\" fret1=\"" + fret[1]
				+ "\" fret2=\"" + fret[2] + "\" fret3=\"" + fret[3]
				+ "\" fret4=\"" + fret[4] + "\" fret5=\"" + fret[5] + "\"/>");
		return xml;
	}
}
