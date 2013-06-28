package cz.derhaa.timeshot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cz.derhaa.timeshot.loader.FileLoad;
import cz.derhaa.timeshot.loader.HttpLoad;
import cz.derhaa.timeshot.loader.Loader;
import cz.derhaa.timeshot.loader.SvnLoad;

/**
 * @author derhaa
 *
 */
public class FactoryTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        //do nothing. ...
    }

    @Test
    public final void test() {
        // http, https
        Loader l = Factory.create("http://test-for-javahl-api.googlecode.com/svn/trunk/", null, null);
        Assert.assertTrue(l instanceof HttpLoad);
        l = Factory.create("https://test-for-javahl-api.googlecode.com/svn/trunk/", null, null);
        Assert.assertTrue(l instanceof HttpLoad);
        
        // svn
        l = Factory.create("svn://test-for-javahl-api.googlecode.com/svn/trunk/", null, null);
        Assert.assertTrue(l instanceof SvnLoad);
        
        // file
        l = Factory.create("file:///test-for-javahl-api.googlecode.com/svn/trunk/neco.xxx", null, null);
        Assert.assertTrue(l instanceof FileLoad);         
    }

}
