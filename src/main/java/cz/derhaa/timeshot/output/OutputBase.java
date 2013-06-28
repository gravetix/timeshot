package cz.derhaa.timeshot.output;


/**
 * @author derhaa
 *
 */
public abstract class OutputBase implements Output {
    
    protected String outputDir;

    public OutputBase(String outputFilePath) {
        this.outputDir = outputFilePath;
    }
}
