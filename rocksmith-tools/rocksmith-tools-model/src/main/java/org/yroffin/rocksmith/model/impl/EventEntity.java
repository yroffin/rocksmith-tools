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

import java.text.DecimalFormat;
import java.util.Locale;

import org.yroffin.rocksmith.model.IEvent;

public class EventEntity implements IEvent {

	private final float time;
	private final String code;

	public static EventEntity factory(float time, String code) {
		EventEntity entity = new EventEntity(time, code);
		return entity;
	}

	private EventEntity(float time, String code) {
		this.time = time;
		this.code = code;
	}

	public StringBuilder asXml(StringBuilder xml) {
		DecimalFormat df = (DecimalFormat) DecimalFormat
				.getNumberInstance(Locale.ENGLISH);
		df.setMaximumFractionDigits(3);
		df.setMinimumFractionDigits(3);
		df.setDecimalSeparatorAlwaysShown(true);
		xml.append("\n<event time=\"" + df.format(time) + "\" number=\"" + code
				+ "\"/>");
		return xml;
	}
}
