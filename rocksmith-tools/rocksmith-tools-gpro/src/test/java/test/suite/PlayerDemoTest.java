/*
 * Created on Mar 21, 2005
 */
package test.suite;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import junit.framework.TestCase;
import test.songs.SongArchive;
import test.songs.SongDefinition;
import test.tools.MockMidiPlayer;
import dguitar.players.sound.midi.demo.LinePrinter;
import dguitar.players.sound.midi.demo.PlayerDemo;

/**
 * @author crnash
 */
public class PlayerDemoTest extends TestCase {
	class PrintStreamLinePrinter implements LinePrinter {
		FileOutputStream fos;
		PrintStream ps;

		PrintStreamLinePrinter(String resultsFile) throws FileNotFoundException {
			fos = new FileOutputStream(resultsFile);
			ps = new PrintStream(fos);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see players.sound.midi.demo.LinePrinter#println(java.lang.String)
		 */
		public void println(String s) {
			ps.println(s);
		}
	}

	class ComparisonLinePrinter implements LinePrinter {
		FileReader fr;
		BufferedReader br;
		String leftSide = "";
		String rightSide = "";

		public String getLeftSide() {
			return leftSide;
		}

		public String getRightSide() {
			return rightSide;
		}

		public void setSides(String l, String r) {
			if (leftSide.equals("")) {
				leftSide = l;
				rightSide = r;
			}
		}

		ComparisonLinePrinter(String resultsFile) throws FileNotFoundException {
			fr = new FileReader(resultsFile);
			br = new BufferedReader(fr);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see players.sound.midi.demo.LinePrinter#println(java.lang.String)
		 */
		public void println(String s) {
			try {
				String testAgainst = br.readLine();
				if (!testAgainst.equals(s)) {
					setSides(testAgainst, s);
				}
			} catch (IOException e) {
				fail("ioexception");
			}
		}
	}

	public void testPlayerDemo() {
		String resultsFile = "src/test/resources/test/output.txt";
		SongDefinition sd = SongArchive.aguaSongDefinition();

		try {
			// LinePrinter lp=new PrintStreamLinePrinter(resultsFile);
			ComparisonLinePrinter lp = new ComparisonLinePrinter(resultsFile);

			PlayerDemo.playerDemo(sd.getGpFileName(), new MockMidiPlayer(), lp);
			assertEquals(lp.getLeftSide(), lp.getRightSide());
		} catch (FileNotFoundException e) {
			fail("file not found exception");
		}
	}
}
