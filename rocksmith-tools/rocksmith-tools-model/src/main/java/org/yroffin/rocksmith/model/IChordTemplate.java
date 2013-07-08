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
 * ChordTemplates
 * 
 * If you wish to use chords in your song, you define them here and then select
 * which chord you want to play during the notation of the song, rather than
 * writing out every note (and optional fingering patterns) every time it's
 * called for during the song.
 * 
 * The ChordTemplate tag has fields for chordName (can be left blank: ""),
 * finger (for each string, state which finger is to be used to fret the note:
 * enables barre-ing) and of course, which fret is to be played. '0' is to be
 * used if the string is to be played open, whilst '-1' is to be used for
 * strings which are not played. finger/fret0 refers to the bottom E string,
 * finger/fret1 to the A string, etc.
 * 
 * Usage:
 * 
 * <song> ... </phraseProperties> <chordTemplates count="3"> <chordTemplate
 * chordName="F" finger0="1" finger1="3" finger2="4" finger3="2" finger4="1"
 * finger5="1" fret0="1" fret1="3" fret2="3" fret3="2" fret4="1" fret5="1"/>
 * <chordTemplate chordName="G" finger0="2" finger1="1" finger2="0" finger3="0"
 * finger4="0" finger5="4" fret0="3" fret1="2" fret2="0" fret3="0" fret4="0"
 * fret5="3"/> <chordTemplate chordName="C" finger0="-1" finger1="3" finger2="2"
 * finger3="0" finger4="1" finger5="0" fret0="-1" fret1="3" fret2="2" fret3="0"
 * fret4="1" fret5="0"/> </chordTemplates> ... </song>
 */
public interface IChordTemplate extends IXmlEntity {

}
