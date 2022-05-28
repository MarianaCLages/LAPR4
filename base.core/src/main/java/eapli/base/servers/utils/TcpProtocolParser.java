package eapli.base.servers.utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TcpProtocolParser {

    public final static int CLIENT_COMMTEST_CLI = 0;
    public final static int SERVER_CLOSE_CONNECTION = 1;
    public final static int SERVER_ACK_CONNECTION = 2;


    public final static int ERROR_MESSAGE = -1;

    public static String readProtocolMessageIntoString(byte[] protocolMessage, int strLenght) {
        byte[] strBytes;

        strBytes = Arrays.copyOfRange(protocolMessage, 0, strLenght);

        return new String(strBytes);
    }

    public static int readServerResponse(byte[] protocolMessage) {
        if (protocolMessage[1] == 2) {
            return SERVER_ACK_CONNECTION;
        } else if (protocolMessage[1] == 0) {
            return SERVER_CLOSE_CONNECTION;
        } else {
            return ERROR_MESSAGE;
        }
    }

    public static int readClientResponse(byte[] protocolMessage) {
        if (protocolMessage[1] == 0) {
            return CLIENT_COMMTEST_CLI;
        } else {
            return ERROR_MESSAGE;
        }
    }

    public static byte[] createProtocolMessageWithAString(String protocolString, int version) {
        byte[] byteArray = protocolString.getBytes(StandardCharsets.UTF_8);

        int strSize = byteArray.length;
        byte[] protocolMessage = new byte[4 + strSize];

        int d_L1 = strSize % 256;
        int d_L2 = strSize / 256;

        byte dL1Bytes = (byte) d_L1;
        byte dL2Bytes = (byte) d_L2;

        //VERSION
        protocolMessage[0] = (byte) version;

        //CODE
        protocolMessage[1] = 0;

        //DATA SIZE
        protocolMessage[2] = dL1Bytes;
        protocolMessage[3] = dL2Bytes;

        //DATA
        for (int i = 0; i < strSize; i++) {
            protocolMessage[i + 4] = byteArray[i];

        }

        return protocolMessage;
    }


    public static int lenght(byte[] protocolMessage) {
        int aux_1 = 0;
        int aux_2 = 0;

        if (protocolMessage[2] < 0) {
            aux_1 = protocolMessage[2] + 256;
        } else {
            aux_1 = protocolMessage[2];
        }

        if (protocolMessage[3] < 0) {
            aux_2 = protocolMessage[3] + 256;
        } else {
            aux_2 = protocolMessage[3];
        }

        int strLenght = (aux_1 + (aux_2 * 256));
        return strLenght;
    }

}
