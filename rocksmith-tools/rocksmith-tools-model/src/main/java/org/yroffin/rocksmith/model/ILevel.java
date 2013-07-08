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
 * Levels Each of the levels is a different difficulty setting for the song.
 * Contained within each level tag is the entire song written for that
 * difficulty level. It is left for the game to use the phrase iterations to
 * decide which difficulty level to read from and when. Each level states its
 * difficulty, and contains a set of notes, chords, frethandmutes (seemingly
 * always 0 of these), anchors and handshapes:
 * 
 * <song> ... <levels count="2"> <level difficulty="0"> <notes count="12"> ...
 * </notes> <chords count="0"/> <fretHandMutes count="0"/> <anchors count="0"/>
 * <handShapes count="0"/> </level> <level difficulty="1"> ... </level>
 * </levels> </song>
 */
public interface ILevel extends IXmlEntity {

}
