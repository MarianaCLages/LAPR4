package eapli.base.tcpServer.agvManagerManagement.domain;

import eapli.framework.util.Factory;

public class REQUESTS_API_RequestFactory implements Factory<REQUESTS_API_Request> {

    byte[] type;

    public void setRequestType(byte[] request) {
        this.type = request;


    }

    @Override
    public REQUESTS_API_Request build() {
        switch (type[1]) {
            case 0x05:
                return new REQUEST_AGV_POSITION_Request();
            case 0x06:
                return null;
            case 0x07:
                return new REQUEST_ASSIGN_ORDER_Request();
            case 0x08:
                return new REQUEST_FREE_AGV_Request();
            case 0x09:
                return new REQUEST_ORDERS_Request();
            case 0x0A:
                return new AGV_Request_ORDER();
            case 0x0B:
                return new WARN_SERVER_NEW_ORDER();
            case 0x0C:
                return new REQUEST_OCCUPIED_AGV();
            case 0x0D:
                return new REQUEST_FREE_AGV_Request();
            default:
                //TODO: exceção para saber que foi badRequest
        }

        return null;

    }
}
