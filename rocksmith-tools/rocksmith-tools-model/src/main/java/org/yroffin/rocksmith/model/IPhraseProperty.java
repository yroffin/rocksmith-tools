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
 * PhraseProperty
 * 
 * Uncertain. Possibly a way of altering the way automatic difficulty affects a
 * particular phrase? Some songs have them (e.g. Freebird), some don't (e.g.
 * Symphony of Destruction).
 * 
 * Usage:
 * 
 * <song> ... </linkedDiffs> <phraseProperties count="2"> <phraseProperty
 * difficulty="0" empty="0" levelJump="0" phraseId="0" redundant="0"/>
 * <phraseProperty difficulty="-1" empty="0" levelJump="0" phraseId="1"
 * redundant="0"/> </phraseProperties> ... </song>
 */
public interface IPhraseProperty extends IXmlEntity {

}
