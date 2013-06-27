package cz.derhaa.timeshot;

import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;

/**
 * @author derhaa
 *
 */
public class Timeshot {

    private String url;
    private String name;
    private String password;
    private int startRevision;
    private int endRevision;

    public Timeshot() {
        this.url = "http://svn.svnkit.com/repos/svnkit/trunk/doc";
        this.name = "anonymous";
        this.password = "anonymous";
        this.startRevision = 0;
        this.endRevision = -1;//HEAD (the latest) revision        
        setupLibrary();// TODO tohle jeste nejak rozmyslet
    }

    /*
     * Initializes the library to work with a repository via 
     * different protocols.
     */
    private static void setupLibrary() {
        /* For using over http:// and https:// */
        DAVRepositoryFactory.setup();
        // /*
        // * For using over svn:// and svn+xxx://
        // */
        // SVNRepositoryFactoryImpl.setup();
        //
        // /*
        // * For using over file:///
        // */
        // FSRepositoryFactory.setup();
    }    
}
