package eapli.base.tcpServer.presentation;

import eapli.base.Application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

public final class CsvTcpProtocolServer {

    private static final Logger LOGGER = LogManager.getLogger(CsvTcpProtocolServer.class);

    /**
     * Avoid instantiation of this class.
     */

    private CsvTcpProtocolServer() {

    }

    public static void main(final String[] args) {
        LOGGER.info("Configuring the TCP Server::");

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(),
                new PlainTextEncoder());

        LOGGER.info("Starting the server socket::");

        try {
            TcpSrv.serverRun(Integer.valueOf(Application.settings().getTcpServerServerSocketPort()));
        } catch (Exception e) {
            LOGGER.error("There was an error while opening the server socket!");
        }

        LOGGER.info("Exiting the daemon::");
        System.exit(0);
    }
}

