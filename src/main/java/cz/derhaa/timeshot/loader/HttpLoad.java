package cz.derhaa.timeshot.loader;

import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;

/**
 * For using over http:// and https://
 * @author derhaa
 */
public class HttpLoad extends BaseLoad {
    /**
     * @param url
     * @param name
     * @param password
     */
    public HttpLoad(final String url, final String name, final String password) {
        super(url, name, password);
    }
    /* (non-Javadoc)
     * @see cz.derhaa.timeshot.LoadStrategy#setupLibrary()
     */
    protected final void setupLibrary() {
        DAVRepositoryFactory.setup();
    }
}
