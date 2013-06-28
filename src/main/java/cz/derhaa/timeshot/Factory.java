package cz.derhaa.timeshot;

import cz.derhaa.timeshot.loader.FileLoad;
import cz.derhaa.timeshot.loader.HttpLoad;
import cz.derhaa.timeshot.loader.Loader;
import cz.derhaa.timeshot.loader.SvnLoad;

/**
 * @author derhaa
 *
 */
public class Factory {
    /**
     * Build concrete loader which inicialize specific SVN factory ${link org.tmatesoft.svn.core.io.SVNRepositoryFactory}
     * @param url
     * @param name
     * @param password
     * @return loader
     */
    public static final Loader create(String url, String name, String password) {
        if(url == null || url.isEmpty()) throw new RuntimeException("Repository URL cannot be null");
        if(url.indexOf("http://") != -1 || url.indexOf("https://") != -1) {
            return new HttpLoad(url, name, password);
        } else if (url.indexOf("file:///") != -1) {
            return new FileLoad(url, name, password);
        } else if (url.indexOf("svn://") != -1 || url.indexOf("svn+") != -1) {
            return new SvnLoad(url, name, password);
        }
        throw new RuntimeException("Unsuported repository url");
    }
    
}
