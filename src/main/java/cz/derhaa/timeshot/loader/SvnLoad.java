package cz.derhaa.timeshot.loader;

import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;

/**
 * For using over svn:// and svn+xxx://
 * @author derhaa
 */
public class SvnLoad extends BaseLoad {
    /**
     * @param url
     * @param name
     * @param password
     */
    public SvnLoad(final String url, final String name, final String password) {
        super(url, name, password);
    }
    /* (non-Javadoc)
     * @see cz.derhaa.timeshot.LoadStrategy#setupLibrary()
     */
    protected final void setupLibrary() {
        SVNRepositoryFactoryImpl.setup();
    }
}
