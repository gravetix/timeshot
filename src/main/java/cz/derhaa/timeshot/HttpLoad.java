package cz.derhaa.timeshot;

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
    public HttpLoad(String url, String name, String password) {
        super(url, name, password);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see cz.derhaa.timeshot.LoadStrategy#setupLibrary()
     */
    public void setupLibrary() {
        DAVRepositoryFactory.setup();
    }

}
