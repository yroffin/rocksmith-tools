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
package org.yroffin.rocksmith.model;

import java.util.Date;
import java.util.List;

/**
 * SongEntity model based on
 * https://code.google.com/p/rocksmith-custom-song-creator
 * /wiki/DefiningCustomSongXmlFiles
 */
public interface ISongEntity extends IXmlEntity {

	/**
	 * Title This is the name of the song. Uncertain as to where this appears
	 * in-game.
	 * 
	 * Usage:
	 * 
	 * <song> <title>Nice Song</title> ... </song>
	 * 
	 * @param string
	 */
	void setTitle(String string);

	/**
	 * Arrangement Rocksmith allows for different 'arrangements' for the same
	 * song. This can be whether you're playing the guitar or bass or singing,
	 * or which guitar part you are playing. Uncertain as to where this appears
	 * in-game.
	 * 
	 * Usage:
	 * 
	 * <song> <title>Nice Song</title> <arrangement>Bass</arrangement> ...
	 * </song>
	 * 
	 * @param string
	 */
	void setArrangement(String string);

	/**
	 * Part I have no idea. So far I've just used '1'
	 * 
	 * Usage:
	 * 
	 * <song> ... <arrangement>Bass</arrangement> <part>1</part> ... </song>
	 * 
	 * @param i
	 */
	void setPart(int i);

	/**
	 * Offset A time difference between the start of the audio file and the
	 * start of the sng file? or a time difference between the start of both of
	 * these and the start of gameplay? Please clarify. So far I've just used
	 * '0.000'
	 * 
	 * Usage:
	 * 
	 * <song> ... <part>1</part> <offset>0.000</offset> ... </song>
	 * 
	 * @param d
	 */
	void setOffset(double d);

	/**
	 * SongLength
	 * 
	 * This is the length of the song, in seconds.
	 * 
	 * Usage:
	 * 
	 * <song> ... <offset>0.000</offset> <songLength>180.300</songLength> ...
	 * </song>
	 * 
	 * @param d
	 */
	void setSongLength(double d);

	double getSongLength();

	/**
	 * LastConversionDateTime
	 * 
	 * How recent the file is. US Date format? Uncertain how important this is.
	 * 
	 * Usage:
	 * 
	 * <song> ... <songLength>154.300</songLength>
	 * <lastConversionDateTime>11-6-12 7:5</lastConversionDateTime> ... </song>
	 * 
	 * @param date
	 */
	void setLastConversionDateTime(Date date);

	IEbeat add(IEbeat phrase);

	void add(IPhraseEntity phrase);

	void add(IPhraseIterationEntity phraseIterationEntity);

	void add(ILinkedDiff linkedDiff);

	void add(IPhraseProperty phraseProperty);

	IChordTemplate add(IChordTemplate chordTemplate);

	ISection add(ISection section);

	IEvent add(IEvent event);

	ILevel add(ILevel level);

	List<IPhraseEntity> getPhrases();

	IMeasure add(IMeasure factory);

	List<IMeasure> getMeasures();

	List<IEbeat> getEbeats();

	void setArtistName(String artist);

	void setAlbum(String album);

	void setAlbumYear(String date);
}
