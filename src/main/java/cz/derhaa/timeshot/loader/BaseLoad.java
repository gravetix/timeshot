package cz.derhaa.timeshot.loader;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
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
    @SuppressWarnings({ "rawtypes" })
    public final List<Entry> getEntries(final long startRevision, final long endRevision) {
        try {
            Collection logEntries = Collections.emptyList();
            logEntries = repository.log(new String[] {""}, null, startRevision, endRevision, true, true);
            
            List<Entry> retval = new LinkedList<Entry>();
            for (Iterator entries = logEntries.iterator(); entries.hasNext();) {
                SVNLogEntry logEntry = (SVNLogEntry) entries.next();
                
                StringBuilder sb = new StringBuilder("");
                if (logEntry.getChangedPaths().size() > 0) {
                    Set pathsSet = logEntry.getChangedPaths().keySet();
                    for (Iterator paths = pathsSet.iterator(); paths.hasNext();) {
                        SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry
                                .getChangedPaths().get(paths.next());
                        /*
                         * SVNLogEntryPath.getPath returns the changed path itself;
                         * 
                         * SVNLogEntryPath.getType returns a charecter describing
                         * how the path was changed ('A' - added, 'D' - deleted or
                         * 'M' - modified);
                         * 
                         * If the path was copied from another one (branched) then
                         * SVNLogEntryPath.getCopyPath &
                         * SVNLogEntryPath.getCopyRevision tells where it was copied
                         * from and what revision the origin path was at.
                         */
                        sb.append(entryPath.getType()).append(" ");
                        sb.append(entryPath.getPath()).append(" ");
                        sb.append(((entryPath.getCopyPath() != null) ? " (from "
                                        + entryPath.getCopyPath() + " revision "
                                        + entryPath.getCopyRevision() + ")" : ""));
                        sb.append("\n");
                    }                    
                }
                
                Entry entry = new Entry(
                        logEntry.getRevision(), 
                        logEntry.getAuthor(),
                        logEntry.getDate(),
                        logEntry.getMessage(),
                        sb.toString());
                retval.add(entry);
            }
            return retval;
        } catch (SVNException e) {
            throw new RuntimeException("Problem loading logentries from repository", e);
        }
    }
    
    /* (non-Javadoc)
     * @see cz.derhaa.timeshot.Load#getEntries(org.joda.time.DateTime, org.joda.time.DateTime)
     */
    public final List<Entry> getEntries(final DateTime from, final DateTime to) {
        try {
            long startRevision = repository.getDatedRevision(from.toDate());
            long endRevision = repository.getDatedRevision(to.toDate());
            return getEntries(startRevision, endRevision);
        } catch (SVNException e) {
            throw new RuntimeException("Problem loading revision for dateFrom: "+from+ " and dateTo: "+to, e);
        }
    }
    
    abstract protected void setupLibrary();
}
