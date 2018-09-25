package example.com.playandroid.network.exception;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/9/1
 * @updateAuthor $Author$
 */
public class ApiException extends RuntimeException {
    private int code;
    private String json;

    public ApiException(){}

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ApiException(String message, int code, String json) {
        super(message);
        this.code = code;
        this.json = json;
    }

    public int getCode() {
        return code;
    }

    public String getJson() {
        return json;
    }

    public ApiException(String message){super(message);}

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
