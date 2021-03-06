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

import org.yroffin.rocksmith.model.ConvertTools;
import org.yroffin.rocksmith.model.IPhraseEntity;
import org.yroffin.rocksmith.model.IPhraseIterationEntity;

public class PhraseIterationEntity implements IPhraseIterationEntity {

	private final double time;
	private final IPhraseEntity phrase;

	public static PhraseIterationEntity factory(double time, int id) {
		PhraseIterationEntity entity = new PhraseIterationEntity(time, id);
		return entity;
	}

	private PhraseIterationEntity(double time, int id) {
		this.time = time;
		this.phrase = PhraseEntity.getById(id);
	}

	public StringBuilder asXml(StringBuilder xml) {
		xml.append("\n    <phraseIteration time=\"" + ConvertTools.format(time)
				+ "\" phraseId=\"" + phrase.getId() + "\"/>");
		return xml;
	}

	@Override
	public String toString() {
		return "PhraseIterationEntity [time=" + ConvertTools.format(time)
				+ ", phrase=" + phrase + "]";
	}
}
