/*
 * Created on Mar 21, 2005
 */
package test.suite;

import java.util.LinkedList;
import java.util.List;

import javax.sound.midi.MidiEvent;

import junit.framework.TestCase;
import test.tools.MidiTools;

/**
 * @author crnash
 */
public abstract class MidiTestCase extends TestCase {

	protected void compareMIDIFiles(String originalFile, String outputFile,
			int[] channelMap, String[] eventRemap) throws Exception {
		// call the simple midi loaders
		List<MidiEvent> original = new LinkedList<MidiEvent>();
		int originalPPQ = MidiTools.load(originalFile, original);

		List<MidiEvent> output = new LinkedList<MidiEvent>();
		int outputPPQ = MidiTools.load(outputFile, output);

		MidiTools.remapTicks(original, originalPPQ, outputPPQ);

		// note that event remapping should be performed HERE. This allows
		// the filters, remaps, and prioritization to include any new events
		// we might have managed to create.
		if (eventRemap != null) {
			MidiTools.remapEvents(output, eventRemap);
		}

		MidiTools.filterOptionalMeta(original);
		MidiTools.filterOptionalMeta(output);

		MidiTools.remapNoteOff(original);
		MidiTools.remapNoteOff(output);

		// the originalFile has a chance that it contains data on the effects
		// channels, so we filter out any controllers on it and
		// remap the note data
		if (channelMap != null) {
			for (int i = 0; i < channelMap.length; i += 2) {
				int mainChannel = channelMap[i];
				int effectsChannel = channelMap[i + 1];
				if (effectsChannel != mainChannel) {
					MidiTools.remapChannel(original, effectsChannel,
							mainChannel);
				}
			}
		}

		MidiTools.prioritizeByChannel(original);
		MidiTools.prioritizeByChannel(output);

		while ((!original.isEmpty()) && (!output.isEmpty())) {
			MidiEvent e1 = original.remove(0);
			MidiEvent e2 = output.remove(0);

			String s1 = MidiTools.stringize(e1);
			String s2 = MidiTools.stringize(e2);

			assertEquals(s1, s2);
		}
		if (!original.isEmpty()) {
			MidiEvent e = original.get(0);
			assertTrue(
					"additional event in original " + MidiTools.stringize(e),
					original.isEmpty());
		}
		assertTrue(output.isEmpty());
	}

}
