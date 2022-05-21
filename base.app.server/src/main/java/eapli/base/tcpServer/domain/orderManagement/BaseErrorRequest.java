


package eapli.base.tcpServer.domain.orderManagement;

import eapli.framework.csv.CsvRecord;

/**
 *
 * @author Paulo Gandra Sousa 03/06/2020
 *
 */

public abstract class BaseErrorRequest extends BookingProtocolRequest {

    private final String errorDescription;

    protected BaseErrorRequest(final String request, final String errorDescription) {
        super(null, request);
        this.errorDescription = errorDescription;
    }

    protected BaseErrorRequest(final String request) {
        super(null, request);
        this.errorDescription = null;
    }

    @Override
    public String execute() {
        // nothing to do, just build the response
        return buildResponse();
    }

    protected String buildResponse() {
        final Object[] fields = {
                messageType(),
                request,
                errorDescription
        };
        final boolean[] mask = { false, true, true };
        return CsvRecord.valueOf(fields, mask).toString() + "\n";
    }

    protected abstract String messageType();
}
