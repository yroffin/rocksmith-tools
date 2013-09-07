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
 * Understanding Notes Now lets take a look at some sample notes: Specifically
 * at the "Note time" portion. (This is when the note appears).
 * 
 * Synchronizing the note with the beat of the measure brings an accurate
 * display as well as error-free compiling.
 * 
 * <notes count="8"> <note time="7.100" bend="0" fret="3" hammerOn="0"
 * harmonic="0" hopo="0" ignore="0" palmMute="0" pullOff="0" slideTo="-1"
 * string="0" sustain="0" tremolo="0"/> <note time="7.600" bend="0" fret="0"
 * hammerOn="0" harmonic="0" hopo="0" ignore="0" palmMute="0" pullOff="0"
 * slideTo="-1" string="1" sustain="0" tremolo="0"/> <note time="8.100" bend="0"
 * fret="2" hammerOn="0" harmonic="0" hopo="0" ignore="0" palmMute="0"
 * pullOff="0" slideTo="-1" string="1" sustain="0" tremolo="0"/> <note
 * time="8.600" bend="0" fret="3" hammerOn="0" harmonic="0" hopo="0" ignore="0"
 * palmMute="0" pullOff="0" slideTo="-1" string="1" sustain="0" tremolo="0"/>
 * <note time="9.100" bend="0" fret="0" hammerOn="0" harmonic="0" hopo="0"
 * ignore="0" palmMute="0" pullOff="0" slideTo="-1" string="2" sustain="0"
 * tremolo="0"/> <note time="9.600" bend="0" fret="2" hammerOn="0" harmonic="0"
 * hopo="0" ignore="0" palmMute="0" pullOff="0" slideTo="-1" string="2"
 * sustain="0" tremolo="0"/> </notes> (Yes these measures can get more intense.
 * "Sweet Child O mine" for example, has seven notes in one measure)
 */
public interface INote extends IXmlEntity {
	public void addSustain(long sustain);

	public long getTime();

	public void setSlideTo(int value);

	public int getFret();

	public boolean isSlide();
}
