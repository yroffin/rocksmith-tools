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
import org.yroffin.rocksmith.model.ISection;

public class SectionEntity implements ISection {

	private final String name;
	private final int number;
	private final double startTime;

	public static SectionEntity factory(String name, int number,
			double startTime) {
		SectionEntity entity = new SectionEntity(name, number, startTime);
		return entity;
	}

	private SectionEntity(String name, int number, double startTime) {
		this.name = name;
		this.number = number;
		this.startTime = startTime;
	}

	public StringBuilder asXml(StringBuilder xml) {
		xml.append("\n    <section name=\"" + name + "\" number=\"" + number
				+ "\" startTime=\"" + ConvertTools.format(startTime) + "\"/>");
		return xml;
	}
}
