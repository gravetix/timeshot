package cz.derhaa.timeshot.loader;

import java.util.List;

import org.joda.time.DateTime;

import cz.derhaa.timeshot.entity.Entry;

/**
 * @author derhaa
 *
 */
public interface Load {

    List<Entry> getEntries(long startRevision, long endRevision);
    
    List<Entry> getEntries(DateTime from, DateTime to);
    
}
