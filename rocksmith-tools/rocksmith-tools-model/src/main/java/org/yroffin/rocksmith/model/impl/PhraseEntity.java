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

import java.util.ArrayList;
import java.util.List;

import org.yroffin.rocksmith.model.IPhraseEntity;

public class PhraseEntity implements IPhraseEntity {

	private final int disparity;
	private final int ignore;
	private final int maxDifficulty;
	private final int solo;
	private final String name;
	private int id;

	private static List<IPhraseEntity> phrases = new ArrayList<IPhraseEntity>();

	public static IPhraseEntity factory(int disparity, int ignore,
			int maxDifficulty, String name, int solo) {
		IPhraseEntity entity = new PhraseEntity(disparity, ignore,
				maxDifficulty, name, solo);
		entity.setId(phrases.size());
		phrases.add(entity);
		return entity;
	}

	private PhraseEntity(int disparity, int ignore, int maxDifficulty,
			String name, int solo) {
		this.disparity = disparity;
		this.ignore = ignore;
		this.maxDifficulty = maxDifficulty;
		this.solo = solo;
		this.name = name;
	}

	public StringBuilder asXml(StringBuilder xml) {
		xml.append("\n<phrase disparity=\"" + disparity + "\" ignore=\""
				+ ignore + "\" maxDifficulty=\"" + maxDifficulty + "\" name=\""
				+ name + "\" solo=\"" + solo + "\"/>");
		return xml;
	}

	public static IPhraseEntity getById(int id) {
		return PhraseEntity.phrases.get(id);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
