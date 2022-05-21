package eapli.base.tcpServer.domain.orderManagement;

/**
 *
 * @author Paulo Gandra Sousa 01/06/2020
 *
 */



public class ErrorInRequest extends BaseErrorRequest {

    public ErrorInRequest(final String request, final String errorDescription) {
        super(request, errorDescription);
    }

    @Override
    protected String messageType() {
        return "ERROR_IN_REQUEST";
    }
}