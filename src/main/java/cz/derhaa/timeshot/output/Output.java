package cz.derhaa.timeshot.output;

import java.util.List;

import cz.derhaa.timeshot.entity.Entry;

/**
 * @author derhaa
 *
 */
public interface Output {

    void write(List<Entry> entries);
    
}
