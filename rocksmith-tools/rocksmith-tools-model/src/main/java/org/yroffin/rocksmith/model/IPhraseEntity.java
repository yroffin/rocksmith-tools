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

/**
 * Phrases The phrases tag contains a list of the different phrases (or riffs)
 * in the song. As Rocksmith uses a dynamic difficulty mechanic, doing well at
 * playing a particular phrase will increase its difficulty level. A phrase may
 * occur several times in one song, even if their are differences in the notes
 * played, and each of these is known as an iteration. They will be covered
 * later.
 * 
 * The phrases tag contains a count= field stating the number of different
 * phrases, and then each phrase has its own tag.
 * 
 * Each phrase tag contains information about its disparity (unknown, integer),
 * whether to ignore it (how is it ignored? unknown, integer), its maximum
 * difficulty level (starting from zero, so a phrase with four difficulty
 * setting will have maxDifficulty="3", its name, and whether it is a solo (how
 * does this impact gameplay? unknown). It appears that the game uses an empty
 * phrase at the beginning and end of each song, it is unknown how important
 * these are though. They have a max difficulty of 0. Various names have been
 * documented for the opening phrase ("COUNT", "COUNTOFF", "Introbar" etc.), so
 * far only one has been for the closing one ("END")
 * 
 * Usage:
 * 
 * <song> ... <lastConversionDateTime>11-6-12 7:5</lastConversionDateTime>
 * <phrases count="4"> <phrase disparity="0" ignore="0" maxDifficulty="0"
 * name="COUNT" solo="0"/> <phrase disparity="0" ignore="0" maxDifficulty="4"
 * name="Verse" solo="0"/> <phrase disparity="0" ignore="0" maxDifficulty="3"
 * name="Chorus" solo="0"/> <phrase disparity="0" ignore="0" maxDifficulty="0"
 * name="END" solo="0"/> </phrases> ... </song>
 */
public interface IPhraseEntity extends IXmlEntity {
	public void setId(int size);

	public int getId();

	public int getMeasureId();
}
