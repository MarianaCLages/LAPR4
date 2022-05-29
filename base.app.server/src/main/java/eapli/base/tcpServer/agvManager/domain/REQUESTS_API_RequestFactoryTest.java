package eapli.base.tcpServer.agvManager.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class REQUESTS_API_RequestFactoryTest {

    @Test
    void testBuild() {
        REQUESTS_API_RequestFactory factory = new REQUESTS_API_RequestFactory();
        //Get the agv position
        byte[] request = new byte[]{0x00, 0x05};
        factory.setRequestType(request);
        Assertions.assertEquals(REQUEST_AGV_POSITION_Request.class, factory.build().getClass());
        //Not a valid request
        request = new byte[]{0x00, 0x06};
        factory.setRequestType(request);
        Assertions.assertNull(factory.build());
        //Assign order
        request = new byte[]{0x00, 0x07};
        factory.setRequestType(request);
        Assertions.assertEquals(REQUEST_ASSIGN_ORDER_Request.class, factory.build().getClass());
        //Request free agv
        request = new byte[]{0x00, 0x08};
        factory.setRequestType(request);
        Assertions.assertEquals(REQUEST_FREE_AGV_Request.class, factory.build().getClass());
        //Request orders
        request = new byte[]{0x00, 0x09};
        factory.setRequestType(request);
        Assertions.assertEquals(REQUEST_ORDERS_Request.class, factory.build().getClass());
        //AGV request order
        request = new byte[]{0x00, 0x0A};
        factory.setRequestType(request);
        Assertions.assertEquals(AGV_Request_ORDER.class, factory.build().getClass());
        //Warning server that there is a new order
        request = new byte[]{0x00, 0x0B};
        factory.setRequestType(request);
        Assertions.assertEquals(WARN_SERVER_NEW_ORDER.class, factory.build().getClass());
        //Request occupied agv
        request = new byte[]{0x00, 0x0C};
        factory.setRequestType(request);
        Assertions.assertEquals(REQUEST_OCCUPIED_AGV.class, factory.build().getClass());
        //Request free agv
        request = new byte[]{0x00, 0x0D};
        factory.setRequestType(request);
        Assertions.assertEquals(REQUEST_FREE_AGV_Request.class, factory.build().getClass());
    }
}