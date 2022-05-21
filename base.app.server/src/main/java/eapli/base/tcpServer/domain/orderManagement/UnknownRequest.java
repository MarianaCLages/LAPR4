package eapli.base.tcpServer.domain.orderManagement;

/**
 *
 * @author Paulo Gandra Sousa 01/06/2020
 *
 */

public class UnknownRequest extends BaseErrorRequest {

    public UnknownRequest(final String inputLine) {
        super(inputLine);
    }

    @Override
    protected String messageType() {
        return "UNKNOWN_REQUEST";
    }
}
