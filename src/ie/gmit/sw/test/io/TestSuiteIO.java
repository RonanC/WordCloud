package ie.gmit.sw.test.io;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Ronan Test Suite for the graphics package Each class in the package
 *         has its own test class
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestDataProcessor.class, TestDataReaderFactory.class, TestFileDataReader.class, TestStopWords.class, TestUrlDataReader.class })
public class TestSuiteIO {
}
