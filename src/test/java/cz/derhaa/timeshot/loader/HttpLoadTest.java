package cz.derhaa.timeshot.loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import cz.derhaa.timeshot.entity.Entry;

/**
 * @author derhaa
 *
 */
public class HttpLoadTest {

    private HttpLoad object;
    private Properties prop;

    public HttpLoadTest() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read properties file", ex);
        }
    }
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        object = new HttpLoad(prop.getProperty("url"), prop.getProperty("name   "), prop.getProperty("password"));
    }

    @Test
    public final void testLoadEntriesByRevision() {
        List<Entry> entries = object.getEntries(1, 3);
        Assert.assertNotNull(entries);
        Assert.assertFalse(entries.isEmpty());
        Assert.assertEquals(3, entries.size());
        
        ReflectionAssert.assertPropertyLenientEquals("revision", Arrays.asList(1,2,3), entries);
    }

    @Test
    public final void testLoadEntriesByDate() {
        DateTime from = new DateTime(2013, 3, 1, 0, 0, 0);
        DateTime to = new DateTime(2013, 7, 1, 0, 0, 0);
        List<Entry> entries = object.getEntries(from, to);
        Assert.assertNotNull(entries);
        Assert.assertFalse(entries.isEmpty());
        Assert.assertEquals(6, entries.size());
        ReflectionAssert.assertPropertyLenientEquals("revision", Arrays.asList(1,2,3,4,5,6), entries);
        
        from = new DateTime(2013, 3, 1, 0, 0, 0);
        to = new DateTime(2013, 3, 30, 0, 0, 0);
        entries = object.getEntries(from, to);
        Assert.assertNotNull(entries);
        Assert.assertFalse(entries.isEmpty());
        Assert.assertEquals(5, entries.size());
        ReflectionAssert.assertPropertyLenientEquals("revision", Arrays.asList(1,2,3,4,5), entries);
        
        from = new DateTime(2013, 6, 1, 0, 0, 0);
        to = new DateTime(2013, 6, 30, 0, 0, 0);
        entries = object.getEntries(from, to);
        Assert.assertNotNull(entries);
        Assert.assertFalse(entries.isEmpty());
        Assert.assertEquals(2, entries.size());
        ReflectionAssert.assertPropertyLenientEquals("revision", Arrays.asList(5,6), entries);
    }    
}
