package cz.derhaa.timeshot.loader;

import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;

/**
 * For using over file:///
 * @author derhaa
 */
public class FileLoad extends BaseLoad {
    /**
     * @param url
     * @param name
     * @param password
     */
    public FileLoad(final String url, final String name, final String password) {
        super(url, name, password);
    }
    /* (non-Javadoc)
     * @see cz.derhaa.timeshot.LoadStrategy#setupLibrary()
     */
    protected final void setupLibrary() {
        FSRepositoryFactory.setup();
    }
}
