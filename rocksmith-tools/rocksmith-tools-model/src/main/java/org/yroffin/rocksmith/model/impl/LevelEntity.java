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

import org.yroffin.rocksmith.model.IAnchor;
import org.yroffin.rocksmith.model.ILevel;
import org.yroffin.rocksmith.model.INote;
import org.yroffin.rocksmith.model.IXmlEntity;

public class LevelEntity implements ILevel {

	private final int difficulty;

	private final List<IXmlEntity> xmlNotes = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlAnchors = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlChords = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlFretHandMutes = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlHandShapes = new ArrayList<IXmlEntity>();

	private final List<INote> notes = new ArrayList<INote>();
	private final List<IAnchor> anchors = new ArrayList<IAnchor>();

	public static ILevel factory(int difficulty) {
		LevelEntity entity = new LevelEntity(difficulty);
		return entity;
	}

	private LevelEntity(int difficulty) {
		this.difficulty = difficulty;
	}

	public void add(INote note) {
		xmlNotes.add(note);
		notes.add(note);
	}

	public void add(IAnchor anchor) {
		xmlAnchors.add(anchor);
		anchors.add(anchor);
	}

	public StringBuilder asXml(StringBuilder xml) {
		xml.append("\n    <level difficulty=\"" + difficulty + ">");
		EntitiesAsXml(xml, "notes", xmlNotes);
		EntitiesAsXml(xml, "chords", xmlChords);
		EntitiesAsXml(xml, "fretHandMutes", xmlFretHandMutes);
		EntitiesAsXml(xml, "anchors", xmlAnchors);
		EntitiesAsXml(xml, "handShapes", xmlHandShapes);
		xml.append("\n</level>");
		return xml;
	}

	private StringBuilder EntitiesAsXml(StringBuilder xml, String balise,
			List<IXmlEntity> entities) {
		if (entities.size() > 0) {
			xml.append("\n      <" + balise + " count=\"" + entities.size()
					+ "\">");
			for (IXmlEntity entity : entities) {
				entity.asXml(xml);
			}
			xml.append("\n      </" + balise + ">");
		} else {
			xml.append("\n      <" + balise + " count=\"0\"/>");
		}
		return xml;
	}
}
