/*
 * Created on Mar 21, 2005
 */
package test.suite;

import junit.framework.TestCase;
import dguitar.adaptors.song.Marker;
import dguitar.adaptors.song.SongMeasure;
import dguitar.adaptors.song.SongPhrase;
import dguitar.adaptors.song.SongPhraseList;
import dguitar.adaptors.song.TimeSignature;
import dguitar.adaptors.song.impl.SongMeasureImpl;
import dguitar.adaptors.song.impl.SongPhraseListImpl;

/**
 * @author crnash
 */
public class SongPhraseListImplTest extends TestCase {
	SongPhraseList part;

	@Override
	public void setUp() {
		part = new SongPhraseListImpl();

		for (int i = 0; i < 5; i++) {
			SongMeasure measure = new SongMeasureImpl(i, 10080,
					new TimeSignature(4, 4), new Marker("default"));
			part.addPhrase(measure);
		}
	}

	public void testGetScoreMeasureCount() {
		assertEquals(5, part.getScoreMeasureCount());
	}

	public void testGetScoreMeasure() {
		for (int i = 0; i < 5; i++) {
			SongMeasure measure = part.getScoreMeasure(i);
			assertEquals(i, measure.getIndex());
		}
	}

	public void testGetPerformanceMeasureCount() {
		assertEquals(5, part.getPerformanceMeasureCount());
	}

	public void testGetPerformanceMeasure() {
		for (int i = 0; i < 5; i++) {
			SongMeasure measure = part.getPerformanceMeasure(i);
			assertEquals(i, measure.getIndex());
		}
	}

	public void testGetPhrase() {
		for (int i = 0; i < 5; i++) {
			SongPhrase phrase = part.getPhrase(i);
			assertTrue(phrase instanceof SongMeasure);
			SongMeasure measure = (SongMeasure) phrase;
			assertEquals(i, measure.getIndex());
		}
	}

	public void testGetPhraseCount() {
		assertEquals(5, part.getPhraseCount());
	}
}
