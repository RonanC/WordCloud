package ie.gmit.sw.test.graphics;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Ronan Test Suite for the graphics package Each class in the package
 *         has its own test class
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestDisplayGraphics.class, TestGraphicsProcessor.class, TestWordAnalyser.class,
		TestWordObject.class })
public class TestSuiteGraphics {
}
