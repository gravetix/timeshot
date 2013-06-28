package cz.derhaa.timeshot.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cz.derhaa.timeshot.entity.Entry;

/**
 * @author derhaa
 *
 */
public class OutputTXT extends OutputBase {

    public OutputTXT(String configFilePath) {
        super(configFilePath);
    }
    /* (non-Javadoc)
     * @see cz.derhaa.timeshot.output.Output#write(java.util.List)
     */
    @Override
    public void write(List<Entry> entries) {
         File file = new File(outputDir);
         StringBuilder sb = new StringBuilder();
         BufferedWriter writer = null;
         try {
             file.createNewFile();
             writer = new BufferedWriter(new FileWriter(file, false));
             for (Entry entry : entries) {
                 sb.append("revision: ").append(entry.getRevision()).append("\n");
                 sb.append("author: ").append(entry.getAuthor()).append("\n");
                 sb.append("date: ").append(entry.getDate()).append("\n\n");
                 sb.append("message: ").append("\n").append(entry.getMessage()).append("\n\n");
                 sb.append("changed paths: ").append("\n").append(entry.getChangedPaths());
                 sb.append("---------------------------------").append("\n");
                 writer.write(sb.toString());
                 writer.newLine();            
            }                
         } catch (IOException e) {
             throw new RuntimeException(e);
         } finally {
             if(writer != null) {
                 try {
                     writer.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
    }

}
