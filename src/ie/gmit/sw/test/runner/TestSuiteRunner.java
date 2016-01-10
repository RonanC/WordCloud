package ie.gmit.sw.test.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ie.gmit.sw.test.graphics.TestSuiteGraphics;
import ie.gmit.sw.test.io.TestSuiteIO;

/**
 * @author Ronan This test suite runs all tests in the project. Test Suite for
 *         the runner class. Runs the graphics and io test suites.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestWindowSystem.class, TestSuiteGraphics.class, TestSuiteIO.class })
public class TestSuiteRunner {
}
