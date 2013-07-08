package org.yroffin.rocksmith.core;

import java.util.Date;

import org.junit.Test;
import org.yroffin.rocksmith.model.ISongEntity;
import org.yroffin.rocksmith.model.impl.PhraseEntity;
import org.yroffin.rocksmith.model.impl.PhraseIterationEntity;
import org.yroffin.rocksmith.model.impl.SongEntity;

public class SimpleTest {

	@Test
	public void test() {
		ISongEntity root = SongEntity.factory();
		/**
		 * simple members
		 */
		root.setTitle("Simple song");
		root.setArrangement("bass");
		root.setPart(1);
		root.setOffset(0.0);
		root.setSongLength(5.5);
		root.setLastConversionDateTime(new Date());
		/**
		 * phrases
		 */
		root.add(PhraseEntity.factory(0, 0, 0, "COUNT", 0));
		root.add(PhraseEntity.factory(0, 0, 3, "Verse", 0));
		root.add(PhraseEntity.factory(0, 0, 4, "Chorus", 0));
		root.add(PhraseEntity.factory(0, 0, 0, "END", 0));
		/**
		 * phraseIterations
		 */
		root.add(PhraseIterationEntity.factory(0, 0));
		root.add(PhraseIterationEntity.factory(12, 1));
		root.add(PhraseIterationEntity.factory(24, 1));
		root.add(PhraseIterationEntity.factory(48, 1));
		root.add(PhraseIterationEntity.factory(52, 2));
		root.add(PhraseIterationEntity.factory(72, 1));
		System.err.println(root.asXml(new StringBuilder()));
	}
}
