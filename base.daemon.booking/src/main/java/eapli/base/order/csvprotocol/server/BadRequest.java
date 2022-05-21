package eapli.base.order.csvprotocol.server;

/**
 *
 * @author Paulo Gandra Sousa 03/06/2020
 *
 */


public class BadRequest extends BaseErrorRequest {

    public BadRequest(final String request, final String errorDescription) {
        super(request, errorDescription);
    }

    @Override
    protected String messageType() {
        return "ERROR_IN_REQUEST";
    }
}
