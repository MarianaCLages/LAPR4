package eapli.base.tcpServer.orderManagement.presentation;

import eapli.base.Application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

public final class TcpOrderProtocolServer {

    private static final Logger LOGGER = LogManager.getLogger(TcpOrderProtocolServer.class);

    /**
     * Avoid instantiation of this class.
     */
    private TcpOrderProtocolServer() {

    }

    public static void main(final String[] args) {
        LOGGER.info("Configuring the Order TCP Server::");

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(),
                new PlainTextEncoder());

        LOGGER.info("Starting the Order server socket::");

        try {
            TcpOrderSrv.serverRun(Integer.valueOf(Application.settings().getOrderTcpServerServerSocketPort()));
        } catch (Exception e) {
            LOGGER.error("There was an error while opening the order server socket!");
        }

        LOGGER.info("Exiting the order server::");
        System.exit(0);
    }
}

