package cz.derhaa.timeshot.output;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import cz.derhaa.timeshot.entity.Entry;
import cz.derhaa.timeshot.loader.HttpLoad;

/**
 * @author derhaa
 *
 */
public class OutputTxtTest {

    private OutputTXT object;
    private Properties prop;

    public OutputTxtTest() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read properties file", ex);
        }    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        object = new OutputTXT("src/test/resources/output.txt");
    }

    @Test
    public final void test() {
        HttpLoad loader = new HttpLoad(prop.getProperty("url"), prop.getProperty("name"), prop.getProperty("password"));
        List<Entry> entries = loader.getEntries(0, -1);
        object.write(entries);
        
    }

}
