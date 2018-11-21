package graph;

/**
 *
 * @author brunomnsilva
 */
public class InvalidEdgeException extends RuntimeException {


    public InvalidEdgeException() {
        super("The edge is invalid or does not belong to this graph.");
    }

    /**
     *
     * @param string
     */
    public InvalidEdgeException(String string) {
        super(string);
    }
    
}
