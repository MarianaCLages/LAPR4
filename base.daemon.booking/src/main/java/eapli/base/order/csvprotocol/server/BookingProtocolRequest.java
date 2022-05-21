package eapli.base.order.csvprotocol.server;


import eapli.base.catalogmanagement.application.SearchCatalogController;

/**
 *
 * @author Paulo Gandra Sousa 01/06/2020
 *
 */



public abstract class BookingProtocolRequest {

    protected final String request;
    protected final SearchCatalogController controller;

    protected BookingProtocolRequest(final SearchCatalogController controller, final String inputRequest) {
        this.request = inputRequest;
        this.controller = controller;
    }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */



    public abstract String execute();

    /**
     * Indicates the object is a goodbye message, that is, a message that will close the
     * connection to the client.
     *
     * @return {@code true} if the object is a a goodbye message.
     */



    public boolean isGoodbye() {
        return false;
    }

    protected String buildServerError(final String errorDescription) {
        final BaseErrorRequest r = new BaseErrorRequest(request, errorDescription) {

            @Override
            protected String messageType() {
                return "SERVER_ERROR";
            }

        };
        return r.buildResponse();
    }

    protected String buildBadRequest(final String errorDescription) {
        final BaseErrorRequest r = new BaseErrorRequest(request, errorDescription) {

            @Override
            protected String messageType() {
                return "BAD_REQUEST";
            }

        };
        return r.buildResponse();
    }
}
