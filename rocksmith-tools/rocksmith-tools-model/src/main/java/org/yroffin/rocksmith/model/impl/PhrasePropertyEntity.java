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
package org.yroffin.rocksmith.model.impl;

import org.yroffin.rocksmith.model.IPhraseProperty;

public class PhrasePropertyEntity implements IPhraseProperty {

	private final int difficulty;
	private final int empty;
	private final int levelJump;
	private final int phraseId;
	private final int redundant;

	public static PhrasePropertyEntity factory(int difficulty, int empty,
			int levelJump, int phraseId, int redundant) {
		PhrasePropertyEntity entity = new PhrasePropertyEntity(difficulty,
				empty, levelJump, phraseId, redundant);
		return entity;
	}

	private PhrasePropertyEntity(int difficulty, int empty, int levelJump,
			int phraseId, int redundant) {
		assert (PhraseEntity.getById(phraseId) != null);
		this.difficulty = difficulty;
		this.empty = empty;
		this.levelJump = levelJump;
		this.phraseId = phraseId;
		this.redundant = redundant;
	}

	public StringBuilder asXml(StringBuilder xml) {
		xml.append("\n<phraseProperty difficulty=\"" + difficulty
				+ "\" empty=\"" + empty + "\" levelJump=\"" + levelJump
				+ "\" phraseId=\"" + phraseId + "\" redundant=\"" + redundant
				+ "\"/>");
		return xml;
	}
}
