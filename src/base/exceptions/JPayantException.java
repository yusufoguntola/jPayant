package base.exceptions;

/**
 *
 * @author Yusuf Oguntola
 */
public class JPayantException extends Exception {

    private final String exceptionMessage;

    public JPayantException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage() {
        return exceptionMessage;
    }

}
