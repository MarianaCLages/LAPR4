package eapli.base.servers.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TcpProtocolParserTest {

    @Test
    void testParseMessage() {
        byte[] protocolMessage = new byte[15];

        protocolMessage[0] = 0;
        protocolMessage[1] = 0;
        protocolMessage[2] = 0;
        protocolMessage[3] = 79;
        protocolMessage[4] = 76;
        protocolMessage[5] = 65;
        protocolMessage[6] = 0;
        protocolMessage[7] = 0;
        protocolMessage[8] = 0;
        protocolMessage[9] = 0;
        protocolMessage[10] = 0;
        protocolMessage[11] = 0;
        protocolMessage[12] = 0;
        protocolMessage[13] = 0;
        protocolMessage[14] = 0;

        String expected = "OLA";
        String actual = TcpProtocolParser.readProtocolMessageIntoString(protocolMessage, 6);
        assertEquals(expected, actual);

    }


    @Test
    void testWriteMessage() {
        String protocolString = "OLA";

        byte[] protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(protocolString, 0);

        assertEquals(protocolString, TcpProtocolParser.readProtocolMessageIntoString(protocolMessage, 7).substring(1, 4));
    }

    @Test
    void testLeght() {
        String protocolString = "OLA";

        byte[] protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(protocolString, 0);

        assertEquals(protocolString.length(), TcpProtocolParser.readProtocolMessageIntoString(protocolMessage, 7).length() - 1);
    }


}