package cz.derhaa.timeshot;

import java.util.List;

import cz.derhaa.timeshot.entity.Entry;

/**
 * @author derhaa
 *
 */
public interface Load {

    void setupLibrary();
    
    List<Entry> getEntries(long startRevision, long endRevision);
}
