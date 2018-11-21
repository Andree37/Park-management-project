package graph;

/**
 *
 * @author brunomnsilva
 */
public class InvalidVertexException extends RuntimeException {

    public InvalidVertexException() {
        super("The vertex is invalid or does not belong to this graph.");
    }

    /**
     *
     * @param string
     */
    public InvalidVertexException(String string) {
        super(string);
    }
    
}
