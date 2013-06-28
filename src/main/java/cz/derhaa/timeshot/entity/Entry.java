package cz.derhaa.timeshot.entity;

import java.util.Date;

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
    private String changedPaths;
    /**
     * @param revision
     * @param author
     * @param date
     * @param message
     * @param changedPaths
     */
    public Entry(long revision, String author, Date dateRevision, String message, String changedPaths) {
        this.revision = revision;
        this.author = author;
        this.date = new DateTime(dateRevision);
        this.message = message;
        this.changedPaths = changedPaths;
    }
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
    /**
     * @return the changedPaths
     */
    public String getChangedPaths() {
        return changedPaths;
    }
}
