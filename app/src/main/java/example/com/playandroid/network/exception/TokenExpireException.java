package example.com.playandroid.network.exception;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/9/1
 * @updateAuthor $Author$
 */
public class TokenExpireException extends ApiException {
    public TokenExpireException() {
    }

    public TokenExpireException(String message, int code) {
        super(message, code);
    }

    public TokenExpireException(String message, int code, String json) {
        super(message, code, json);
    }

    public TokenExpireException(String message) {
        super(message);
    }

    public TokenExpireException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExpireException(Throwable cause) {
        super(cause);
    }
}
