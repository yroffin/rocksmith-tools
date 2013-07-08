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
 * Anchors Anchors are used to tell the player where on the fretboard to
 * position their left hand, by highlighting four frets on the game's fretboard.
 * This helps the player with their fingering. Open strings being played are
 * displayed as a bar across all four frets highlighted by the anchor, so they
 * are necessary to replicate the style of note display used in the game's
 * original tracks. They also tell the game which part of the fretboard to focus
 * on, so if not used correctly could make it much harder to see which notes are
 * to be played. The anchor tag consists of a start time, and the
 * lowest-numbered fret (i.e. first finger position) of the anchor.
 * 
 * <anchors count="3"> <anchor time="13.429" fret="3"/> <anchor time="30.427"
 * fret="2"/> <anchor time="50.241" fret="12"/> </anchors>
 */
public interface IAnchor extends IXmlEntity {

}
