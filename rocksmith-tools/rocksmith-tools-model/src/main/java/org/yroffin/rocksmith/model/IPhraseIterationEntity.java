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
 * PhraseIterations
 * 
 * Phrase iterations are what allows Rocksmith to identify several parts of a
 * song as being similar, i.e. they contain the same phrase, and so to increase
 * the difficulty setting for them all together. This means that if you have a
 * song with a verse/chorus structure, then increasing your difficulty setting
 * of one verse, for example, will increase the difficulty of all verses (or
 * anything else for that matter) marked as being the same phrase, even if it
 * contains different notes. This is because the notes are not defined within
 * the phrase or phrase iteration, but independently.
 * 
 * The phraseIterations tag contains a count of the total number of iterations
 * of phrases, each with their own phraseIteration tag. The phraseIteration tag
 * contains fields stating when the phrase starts ("time"), and which of the
 * previously identified phrases is to be played ("phraseId").
 * 
 * Usage:
 * 
 * <song> ... <phrases count="4"> <phrase disparity="0" ignore="0"
 * maxDifficulty="0" name="COUNT" solo="0"/> <phrase disparity="0" ignore="0"
 * maxDifficulty="4" name="Verse" solo="0"/> <phrase disparity="0" ignore="0"
 * maxDifficulty="3" name="Chorus" solo="0"/> <phrase disparity="0" ignore="0"
 * maxDifficulty="0" name="END" solo="0"/> </phrases> <phraseIterations
 * count="8"> <phraseIteration time="0.000" phraseId="0"/> <phraseIteration
 * time="12.000" phraseId="1"/> <phraseIteration time="32.000" phraseId="1"/>
 * <phraseIteration time="52.000" phraseId="1"/> <phraseIteration time="72.000"
 * phraseId="2"/> <phraseIteration time="92.000" phraseId="1"/> <phraseIteration
 * time="112.000" phraseId="1"/> <phraseIteration time="132.000" phraseId="2"/>
 * <phraseIteration time="152.000" phraseId="3"/> </phraseIterations> ...
 * </song>
 */
public interface IPhraseIterationEntity extends IXmlEntity {

}
