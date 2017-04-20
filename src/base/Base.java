package base;

import base.exceptions.AuthenticationException;
import base.exceptions.InvalidInputException;
import utils.constants.Implementation;

/**
 *
 * @author Yusuf Oguntola
 */
public class Base {

    /**
     * Startup class for the library. This class instantiates all parameters
     *
     * @param authKey
     * @param implementation
     * @throws base.exceptions.AuthenticationException
     * @throws base.exceptions.InvalidInputException
     */
    String authKey, implementation;

    public Base(String authKey, String implementation) throws AuthenticationException, InvalidInputException {
        if (authKey.equals("")) {
            throw new AuthenticationException("Authentication key cannot be null");
        }
        if (implementation.equals("")) {
            throw new InvalidInputException("Invalid implementation mode");
        }
        if (!implementation.equals(Implementation.DEMO) && !implementation.equals(Implementation.LIVE)) {
            throw new InvalidInputException("Invalid implementation mode. Implementation should be demo or live.");
        }
        this.authKey = authKey;
        this.implementation = implementation;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getImplementation() {
        return implementation;
    }

    public String getUrl(String param) throws InvalidInputException {
        if (implementation.equals(Implementation.DEMO)) {
            return "https://api.demo.payant.ng/" + param;
        } else if (implementation.equals(Implementation.LIVE)) {
            return "https://api.payant.ng/" + param;
        } else {
            throw new InvalidInputException("Invalid implementation mode. Implementation should be demo or live.");
        }
    }

}
