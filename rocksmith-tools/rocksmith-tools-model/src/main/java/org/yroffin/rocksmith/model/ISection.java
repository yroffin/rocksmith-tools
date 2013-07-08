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
 * Sections Sections are used by Rocksmith to split the song up into practisable
 * chunks, and to highlight the current part of the song in blue at the top of
 * the screen during playback (provided that the difficulty for the phrase
 * iterations contained in the section is greater than 0).
 * 
 * Not to be confused with phrases or phrase iterations, sections are how the
 * song is split up into different parts with regards to when they occur, as
 * opposed to what they consist of. For instance, a phrase can occur multiple
 * times within a song, each of which is an iteration of that phrase. Whilst a
 * phrase played during the verse of a song might occur twice, it may still be
 * preferable, when using the riff repeater, to play through the whole verse. In
 * this case, there would be two phrase iterations in that verse, but only one
 * section.
 * 
 * Usage:
 * 
 * <song> ... </ebeats> <sections count="6"> <section name="intro" number="1"
 * startTime="12.000"/> <section name="verse" number="1" startTime="32.000"/>
 * <section name="chorus" number="1" startTime="72.000"/> <section name="verse"
 * number="2" startTime="92.000"/> <section name="chorus" number="2"
 * startTime="132.000"/> <section name="noguitar" number="1"
 * starttime="152.000"/> </sections> ... ...
 */
public interface ISection extends IXmlEntity {

}
