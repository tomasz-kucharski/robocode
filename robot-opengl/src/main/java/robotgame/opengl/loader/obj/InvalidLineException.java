package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-08, 12:50:43
 */
public class InvalidLineException extends Exception {
    public InvalidLineException(String message) {
        super(message);
    }

    public InvalidLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
