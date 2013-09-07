package org.yroffin.rocksmith.core;

import java.util.Date;

import org.junit.Test;
import org.yroffin.rocksmith.model.ILevel;
import org.yroffin.rocksmith.model.ISongEntity;
import org.yroffin.rocksmith.model.impl.AnchorEntity;
import org.yroffin.rocksmith.model.impl.ChordTemplateEntity;
import org.yroffin.rocksmith.model.impl.EbeatEntity;
import org.yroffin.rocksmith.model.impl.EventEntity;
import org.yroffin.rocksmith.model.impl.LevelEntity;
import org.yroffin.rocksmith.model.impl.LinkedDiffEntity;
import org.yroffin.rocksmith.model.impl.NoteEntity;
import org.yroffin.rocksmith.model.impl.PhraseEntity;
import org.yroffin.rocksmith.model.impl.PhraseIterationEntity;
import org.yroffin.rocksmith.model.impl.PhrasePropertyEntity;
import org.yroffin.rocksmith.model.impl.SectionEntity;
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
		root.add(PhraseEntity.factory(0, 0, 0, "COUNT", 0, -1));
		root.add(PhraseEntity.factory(0, 0, 3, "Verse", 0, -1));
		root.add(PhraseEntity.factory(0, 0, 4, "Chorus", 0, -1));
		root.add(PhraseEntity.factory(0, 0, 0, "END", 0, -1));
		/**
		 * phraseIterations
		 */
		root.add(PhraseIterationEntity.factory(0, 0));
		root.add(PhraseIterationEntity.factory(12, 1));
		root.add(PhraseIterationEntity.factory(24, 1));
		root.add(PhraseIterationEntity.factory(48, 1));
		root.add(PhraseIterationEntity.factory(52, 2));
		root.add(PhraseIterationEntity.factory(72, 1));
		/**
		 * LinkedDiffs
		 */
		root.add(LinkedDiffEntity.factory(1, 2));
		root.add(LinkedDiffEntity.factory(1, 3));
		root.add(LinkedDiffEntity.factory(4, 2));
		/**
		 * phraseProperties
		 */
		root.add(PhrasePropertyEntity.factory(0, 0, 0, 0, 0));
		root.add(PhrasePropertyEntity.factory(-1, 0, 0, 1, 0));
		/**
		 * chordTemplates
		 */
		root.add(ChordTemplateEntity.factory("F",
				new int[] { 3, 4, 5, 1, 2, 1 }, new int[] { 3, 4, 5, 1, 5, 2 }));
		root.add(ChordTemplateEntity.factory("G",
				new int[] { 3, 4, 5, 1, 2, 1 }, new int[] { 3, 4, 5, 1, 5, 2 }));
		/**
		 * Ebeats
		 */
		root.add(EbeatEntity.factory(7, 2));
		/**
		 * SectionEntities
		 */
		root.add(SectionEntity.factory("intro", 7, 2));
		root.add(SectionEntity.factory("another", 5, 1));
		/**
		 * Events
		 */
		root.add(EventEntity.factory(7, "B1"));
		root.add(EventEntity.factory(5, "B0"));
		/**
		 * Levels
		 */
		ILevel level = null;
		level = LevelEntity.factory(1);
		level.add(NoteEntity.factory(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
				-1, -1, false));
		level.add(NoteEntity.factory(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
				-1, -1, false));
		level.add(AnchorEntity.factory(0, 1));
		level.add(AnchorEntity.factory(0, 1));
		root.add(level);
		level = LevelEntity.factory(2);
		root.add(level);

		System.err.println(root.asXml(new StringBuilder()));
	}
}
