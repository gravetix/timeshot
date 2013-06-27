package cz.derhaa.timeshot;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import cz.derhaa.timeshot.entity.Entry;

/**
 * @author derhaa
 *
 */
abstract public class BaseLoad implements Load {
    
    private SVNRepository repository;

    public BaseLoad(final String url, final String name, final String password) {
        setupLibrary();
        try {
            /*
             * Creates an instance of SVNRepository to work with the repository.
             * All user's requests to the repository are relative to the
             * repository location used to create this SVNRepository.
             * SVNURL is a wrapper for URL strings that refer to repository locations.
             */
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
            repository.setAuthenticationManager(authManager);            
        } catch (SVNException e) {
            throw new RuntimeException("error while creating an SVNRepository for the location '"+ url + "': " + e.getMessage(), e);
        }        
    }

    /* (non-Javadoc)
     * @see cz.derhaa.timeshot.Load#getEntries()
     */
    public final List<Entry> getEntries(long startRevision, long endRevision) {
        Collection logEntries = Collections.emptyList();
        try {
            logEntries = repository.log(new String[] {""}, null, startRevision, endRevision, true, true);
        } catch (SVNException e) {
            throw new RuntimeException("Problem loading logentries from repository", e);
        }
        return null;
    }
}
