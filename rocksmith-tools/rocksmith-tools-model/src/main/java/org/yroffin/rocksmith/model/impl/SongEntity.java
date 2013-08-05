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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.yroffin.rocksmith.model.IChordTemplate;
import org.yroffin.rocksmith.model.IEbeat;
import org.yroffin.rocksmith.model.IEvent;
import org.yroffin.rocksmith.model.IFretHandMuteTemplate;
import org.yroffin.rocksmith.model.ILevel;
import org.yroffin.rocksmith.model.ILinkedDiff;
import org.yroffin.rocksmith.model.IPhraseEntity;
import org.yroffin.rocksmith.model.IPhraseIterationEntity;
import org.yroffin.rocksmith.model.IPhraseProperty;
import org.yroffin.rocksmith.model.ISection;
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
	private final List<IXmlEntity> xmlChordTemplates = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlFretHandMuteTemplates = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlEbeats = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlSections = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlEvents = new ArrayList<IXmlEntity>();
	private final List<IXmlEntity> xmlLevels = new ArrayList<IXmlEntity>();

	private final List<IPhraseEntity> phrases = new ArrayList<IPhraseEntity>();
	private final List<IPhraseIterationEntity> phraseIterations = new ArrayList<IPhraseIterationEntity>();
	private final List<ILinkedDiff> linkedDiffs = new ArrayList<ILinkedDiff>();
	private final List<IPhraseProperty> phraseProperties = new ArrayList<IPhraseProperty>();
	private final List<IChordTemplate> chordTemplates = new ArrayList<IChordTemplate>();
	private final List<IFretHandMuteTemplate> fretHandMuteTemplates = new ArrayList<IFretHandMuteTemplate>();
	private final List<IEbeat> ebeats = new ArrayList<IEbeat>();
	private final List<ISection> sections = new ArrayList<ISection>();
	private final List<IEvent> events = new ArrayList<IEvent>();
	private final List<ILevel> levels = new ArrayList<ILevel>();

	public static ISongEntity factory() {
		return new SongEntity();
	}

	static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy H:m");

	public StringBuilder asXml(StringBuilder xml) {
		DecimalFormat df = (DecimalFormat) DecimalFormat
				.getNumberInstance(Locale.ENGLISH);
		df.setMaximumFractionDigits(3);
		df.setMinimumFractionDigits(3);
		df.setDecimalSeparatorAlwaysShown(true);

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
			xml.append("\n<offset>" + df.format(offset) + "</offset>");
		}
		if (songLength != -1.0) {
			xml.append("\n<songLength>" + df.format(songLength)
					+ "</songLength>");
		}
		if (lastConversionDateTime != null) {
			xml.append("\n<lastConversionDateTime>"
					+ dateFormat.format(lastConversionDateTime)
					+ "</lastConversionDateTime>");
		}
		EntitiesAsXml(xml, "phrases", xmlPhrases);
		EntitiesAsXml(xml, "phraseIterations", xmlPhraseIterations);
		EntitiesAsXml(xml, "linkedDiffs", xmlLinkedDiffs);
		EntitiesAsXml(xml, "phraseProperties", xmlPhraseProperties);
		EntitiesAsXml(xml, "chordTemplates", xmlChordTemplates);
		EntitiesAsXml(xml, "fretHandMuteTemplates", xmlFretHandMuteTemplates);
		EntitiesAsXml(xml, "ebeats", xmlEbeats);
		EntitiesAsXml(xml, "sections", xmlSections);
		EntitiesAsXml(xml, "events", xmlEvents);
		EntitiesAsXml(xml, "levels", xmlLevels);
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

	public void add(ILinkedDiff linkedDiff) {
		xmlLinkedDiffs.add(linkedDiff);
		linkedDiffs.add(linkedDiff);
	}

	public void add(IPhraseProperty phraseProperty) {
		xmlPhraseProperties.add(phraseProperty);
		phraseProperties.add(phraseProperty);
	}

	public void add(IChordTemplate chordTemplate) {
		xmlChordTemplates.add(chordTemplate);
		chordTemplates.add(chordTemplate);
	}

	public void add(IFretHandMuteTemplate fretHandMuteTemplate) {
		xmlFretHandMuteTemplates.add(fretHandMuteTemplate);
		fretHandMuteTemplates.add(fretHandMuteTemplate);
	}

	public void add(IEbeat ebeat) {
		xmlEbeats.add(ebeat);
		ebeats.add(ebeat);
	}

	public void add(ISection section) {
		xmlSections.add(section);
		sections.add(section);
	}

	public void add(IEvent event) {
		xmlEvents.add(event);
		events.add(event);
	}

	public void add(ILevel level) {
		xmlLevels.add(level);
		levels.add(level);
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
