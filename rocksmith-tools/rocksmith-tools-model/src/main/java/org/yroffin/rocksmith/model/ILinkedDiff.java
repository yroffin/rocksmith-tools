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
 * LinkedDiffs
 * 
 * It would appear (but needs confirming) that LinkedDiffs describe two phrases
 * with linked difficulty levels, that is to say, performing well on one will
 * increase the difficulty of the other, but not the other way around. It seems
 * that performing well on the child phrase will tell the game to increase the
 * difficulty of the parent. This could be useful for phrases which are a
 * variation on a theme, such as a solo which is based on a riff. If the player
 * can play the solo well, this implies that they can play the riff, but the
 * reverse is not necessarily true. Usage:
 * 
 * <song> ... </phraseIterations> <linkedDiffs count="1"> <linkedDiff
 * childId="1" parentId="2"/> </linkedDiffs> ... </song>
 */
public interface ILinkedDiff extends IXmlEntity {

}
