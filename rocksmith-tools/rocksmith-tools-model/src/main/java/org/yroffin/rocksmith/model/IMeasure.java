package org.yroffin.rocksmith.model;

import java.util.List;
import java.util.Map;

/**
 * Internal class to modelize measure
 * 
 */
public interface IMeasure {

	int getNumerator();

	boolean hasMarker();

	int getMeasureId();

	String getMarkerName();

	double getTime();

	INote add(double timestamp, INote note);

	Map<Double, List<INote>> getNotes();

	double getBpm();
}
