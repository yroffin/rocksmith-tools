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
import java.util.Date;
import java.util.List;

import org.yroffin.rocksmith.model.ILinkedDiffs;
import org.yroffin.rocksmith.model.IPhraseEntity;
import org.yroffin.rocksmith.model.IPhraseIterationEntity;
import org.yroffin.rocksmith.model.IPhraseProperties;
import org.yroffin.rocksmith.model.ISongEntity;
import org.yroffin.rocksmith.model.IXmlEntity;

/**
 * SongEntity model based on
 * https://code.google.com/p/rocksmith-custom-song-creator
 * /wiki/DefiningCustomSongXmlFiles
 */
public class SongEntity implements ISongEntity {

	private String title;
	private String arrangement;
	private int part;
	private double offset;
	private double songLength;
	private Date lastConversionDateTime;

	/**
	 * for raw manipulation
	 */
	private final List<IXmlEntity> xmlPhrases = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlPhraseIterations = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlLinkedDiffs = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlPhraseProperties = new ArrayList<IXmlEntity>();

	private final List<IPhraseEntity> phrases = new ArrayList<IPhraseEntity>();
	private final List<IPhraseIterationEntity> phraseIterations = new ArrayList<IPhraseIterationEntity>();
	private final List<ILinkedDiffs> linkedDiffs = new ArrayList<ILinkedDiffs>();
	private final List<IPhraseProperties> phraseProperties = new ArrayList<IPhraseProperties>();

	public static ISongEntity factory() {
		return new SongEntity();
	}

	public StringBuilder asXml(StringBuilder xml) {
		xml.append("<?xml version='1.0' encoding='UTF-8'?>");
		/**
		 * dump song level
		 */
		xml.append("\n<song>");
		/**
		 * title
		 */
		if (title != null) {
			xml.append("\n<title>" + title + "</title>");
		}
		/**
		 * arrangement
		 */
		if (arrangement != null) {
			xml.append("\n<arrangement>" + arrangement + "</arrangement>");
		}
		/**
		 * part
		 */
		if (part != -1) {
			xml.append("\n<part>" + part + "</part>");
		}
		/**
		 * part
		 */
		if (offset != -1.0) {
			xml.append("\n<offset>" + offset + "</offset>");
		}
		EntitiesAsXml(xml, "phrases", xmlPhrases);
		EntitiesAsXml(xml, "phraseIterations", xmlPhraseIterations);
		EntitiesAsXml(xml, "linkedDiffs", xmlLinkedDiffs);
		EntitiesAsXml(xml, "phraseProperties", xmlPhraseProperties);
		xml.append("\n</song>");
		return xml;
	}

	private StringBuilder EntitiesAsXml(StringBuilder xml, String balise,
			List<IXmlEntity> entities) {
		if (entities.size() > 0) {
			xml.append("\n<" + balise + " count = \"" + entities.size() + "\">");
			for (IXmlEntity entity : entities) {
				entity.asXml(xml);
			}
			xml.append("\n</" + balise + ">");
		} else {
			xml.append("\n<" + balise + " count = \"0\"/>");
		}
		return xml;
	}

	public void add(IPhraseEntity phrase) {
		xmlPhrases.add(phrase);
		phrases.add(phrase);
	}

	public void add(IPhraseIterationEntity entity) {
		xmlPhraseIterations.add(entity);
		phraseIterations.add(entity);
	}

	public void add(ILinkedDiffs linkedDiff) {
		xmlLinkedDiffs.add(linkedDiff);
		linkedDiffs.add(linkedDiff);
	}

	public void add(IPhraseProperties phraseProperty) {
		xmlPhraseProperties.add(phraseProperty);
		phraseProperties.add(phraseProperty);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArrangement() {
		return arrangement;
	}

	public void setArrangement(String arrangement) {
		this.arrangement = arrangement;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	public Double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

	public Double getSongLength() {
		return songLength;
	}

	public void setSongLength(double songLength) {
		this.songLength = songLength;
	}

	public Date getLastConversionDateTime() {
		return lastConversionDateTime;
	}

	public void setLastConversionDateTime(Date lastConversionDateTime) {
		this.lastConversionDateTime = lastConversionDateTime;
	}

	public List<IPhraseEntity> getPhrases() {
		return phrases;
	}
}
