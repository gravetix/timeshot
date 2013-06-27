package cz.derhaa.timeshot.entity;

import org.joda.time.DateTime;

/**
 * @author derhaa
 *
 */
public class Entry {
    public Long revision;
    public String author;
    public DateTime date;
    public String message;
    /**
     * @return the revision
     */
    public final Long getRevision() {
        return revision;
    }
    /**
     * @return the author
     */
    public final String getAuthor() {
        return author;
    }
    /**
     * @return the date
     */
    public final DateTime getDate() {
        return date;
    }
    /**
     * @return the message
     */
    public final String getMessage() {
        return message;
    }
}
