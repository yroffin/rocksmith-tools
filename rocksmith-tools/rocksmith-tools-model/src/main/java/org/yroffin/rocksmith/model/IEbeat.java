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
 * Understanding Ebeats In this example we will look at creating the most basic
 * of note display.
 * 
 * Here we have a section called "Ebeats". This requires a vague knowledge of
 * music theory, or just a simple common knowledge of math. According to the
 * formula supplied below.. each beat is given .500 seconds of time. Times that
 * by four and we have a two second measure. Otherwise known as 4/4 time or
 * common time.
 * 
 * Usage:
 * 
 * <song> ... <fretHandMuteTemplates count="0"/> <ebeats count="11"> <ebeat
 * time="7.100" measure="1"/> <ebeat time="7.600" measure="-1"/> <ebeat
 * time="8.100" measure="-1"/> <ebeat time="8.600" measure="-1"/> <ebeat
 * time="9.100" measure="2"/> <ebeat time="9.600" measure="-1"/> <ebeat
 * time="10.100" measure="-1"/> <ebeat time="10.600" measure="-1"/> </ebeats>
 * ...
 */
public interface IEbeat extends IXmlEntity {

	int getMeasureId();

	double getTime();

}
