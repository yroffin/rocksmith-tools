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

import org.yroffin.rocksmith.model.ILinkedDiff;

public class LinkedDiffEntity implements ILinkedDiff {

	private final int childId;
	private final int parentId;

	public static LinkedDiffEntity factory(int time, int id) {
		LinkedDiffEntity entity = new LinkedDiffEntity(time, id);
		return entity;
	}

	private LinkedDiffEntity(int childId, int parentId) {
		assert (PhraseEntity.getById(childId) != null);
		assert (PhraseEntity.getById(parentId) != null);
		this.childId = childId;
		this.parentId = parentId;
	}

	public StringBuilder asXml(StringBuilder xml) {
		xml.append("\n<linkedDiff childId=\"" + childId + "\" parentId=\""
				+ parentId + "\"/>");
		return xml;
	}
}
