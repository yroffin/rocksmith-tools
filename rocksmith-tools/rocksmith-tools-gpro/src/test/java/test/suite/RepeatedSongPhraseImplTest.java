/*
 * Created on Mar 21, 2005
 */
package test.suite;

import junit.framework.TestCase;
import dguitar.adaptors.song.RepeatedSongPhrase;
import dguitar.adaptors.song.SongMeasure;
import dguitar.adaptors.song.SongPhrase;
import dguitar.adaptors.song.SongPhraseList;
import dguitar.adaptors.song.TimeSignature;
import dguitar.adaptors.song.impl.RepeatedSongPhraseImpl;
import dguitar.adaptors.song.impl.SongMeasureImpl;
import dguitar.adaptors.song.impl.SongPhraseListImpl;

/**
 * @author crnash
 */
public class RepeatedSongPhraseImplTest extends TestCase {
	RepeatedSongPhrase phrase;

	@Override
	public void setUp() {
		// make a repeated phrase that is 5 measures long, repeated a total
		// of three times.
		SongPhraseList part = new SongPhraseListImpl();

		for (int i = 0; i < 5; i++) {
			SongMeasure measure = new SongMeasureImpl(i, 10080,
					new TimeSignature(4, 4), null);
			part.addPhrase(measure);
		}

		phrase = new RepeatedSongPhraseImpl(part, 2);
	}

	public void testGetScoreMeasureCount() {
		assertEquals(5, phrase.getScoreMeasureCount());
	}

	public void testGetScoreMeasure() {
		for (int i = 0; i < 5; i++) {
			SongMeasure measure = phrase.getScoreMeasure(i);
			assertEquals(i, measure.getIndex());
		}
	}

	public void testGetPerformanceMeasureCount() {
		assertEquals(15, phrase.getPerformanceMeasureCount());
	}

	public void testGetPerformanceMeasure() {
		for (int i = 0; i < 15; i++) {
			SongMeasure measure = phrase.getPerformanceMeasure(i);
			assertEquals(i % 5, measure.getIndex());
		}
	}

	public void testGetPhrase() {
		// make sure the underlying phrase is a SongPhraseListImpl that is
		// 5 measures long both in score and performance
		SongPhrase subphrase = phrase.getPhrase();
		assertTrue(subphrase instanceof SongPhraseListImpl);
		assertEquals(5, subphrase.getScoreMeasureCount());
		assertEquals(5, subphrase.getPerformanceMeasureCount());
	}

	public void testGetRepeatCount() {
		assertEquals(2, phrase.getRepeatCount());
	}
}
