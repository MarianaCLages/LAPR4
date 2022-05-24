/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettings.class);

    private static final String PROPERTIES_RESOURCE = "application.properties";
    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String SCHEMA_GENERATION_KEY = "javax.persistence.schema-generation.database.action";
    private static final String HIGH_CALORIES_DISH_LIMIT = "HighCaloriesDishLimit";
    private static final String DEFAULT_PATH_TO_WAREHOUSE_PLANT_FILE_KEY = "path.to.warehouse.plant.file";

    private static final String TCP_ORDER_SERVER_DNS = "tcp.order.server.dns";
    private static final String TCP_ORDER_CLIENT_SOCKET_PORT = "tcp.order.client.socket";
    private static final String TCP_ORDER_SERVER_SERVERSOCKET_PORT = "tcp.order.server.socket";

    private static final String TCP_AGVMANAGER_SERVER_DNS = "tcp.agv.server.dns";
    private static final String TCP_AGVMANAGER_CLIENT_SOCKET_PORT = "tcp.agv.client.socket";
    private static final String TCP_AGVMANAGER_SERVER_SERVERSOCKET_PORT = "tcp.agv.server.socket";

    private static final String TCP_SERVER_EXECUTOR_1 = "tcp.app.server.1";
    private static final String TCP_SERVER_EXECUTOR_2 = "tcp.app.server.2";

    private static final String TCP_HTTPS_SERVER_DNS = "tcp.https.server.dns";
    private static final String TCP_HTTPS_CLIENT_SOCKET_PORT = "tcp.https.client.port";
    private static final String TCP_HTTPS_SERVER_SERVERSOCKET_PORT = "tcp.https.server.socket";

    private final Properties applicationProperties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader()
                .getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();

            LOGGER.warn("Loading default properties", exio);
        }
    }

    private void setDefaultProperties() {
        this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "eapli.base.persistence.jpa.JpaRepositoryFactory");
        this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
        this.applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "eapli"
                + ".base");
        this.applicationProperties.setProperty(HIGH_CALORIES_DISH_LIMIT, "300");

        this.applicationProperties.setProperty(DEFAULT_PATH_TO_WAREHOUSE_PLANT_FILE_KEY, "files/warehouse1.json");

        this.applicationProperties.setProperty(TCP_ORDER_SERVER_DNS, "vsgate-s3.dei.isep.ipp.pt");
        this.applicationProperties.setProperty(TCP_ORDER_CLIENT_SOCKET_PORT, "10639");
        this.applicationProperties.setProperty(TCP_ORDER_SERVER_SERVERSOCKET_PORT, "10639");

        this.applicationProperties.setProperty(TCP_AGVMANAGER_SERVER_DNS, "vsgate-s2.dei.isep.ipp.pt");
        this.applicationProperties.setProperty(TCP_AGVMANAGER_CLIENT_SOCKET_PORT, "10639");
        this.applicationProperties.setProperty(TCP_AGVMANAGER_SERVER_SERVERSOCKET_PORT, "2225");

        this.applicationProperties.setProperty(TCP_SERVER_EXECUTOR_1, "1");
        this.applicationProperties.setProperty(TCP_SERVER_EXECUTOR_2, "2");

        this.applicationProperties.setProperty(TCP_HTTPS_SERVER_DNS, "vs-gate.dei.isep.ipp.pt");
        this.applicationProperties.setProperty(TCP_HTTPS_CLIENT_SOCKET_PORT, "30639");
        this.applicationProperties.setProperty(TCP_HTTPS_SERVER_SERVERSOCKET_PORT, "2228");

    }

    public Boolean isMenuLayoutHorizontal() {
        return "horizontal"
                .equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    public String getWarehousePlantFile() {
        return this.applicationProperties.getProperty(DEFAULT_PATH_TO_WAREHOUSE_PLANT_FILE_KEY);
    }

    public String getOrderTcpServerDns() {
        return this.applicationProperties.getProperty(TCP_ORDER_SERVER_DNS);
    }

    public String getOrderTcpClientSocketPort() {
        return this.applicationProperties.getProperty(TCP_ORDER_CLIENT_SOCKET_PORT);
    }

    public String getOrderTcpServerServerSocketPort() {
        return this.applicationProperties.getProperty(TCP_ORDER_SERVER_SERVERSOCKET_PORT);
    }

    public String getTcpAgvManagerClientSocketPort() {
        return this.applicationProperties.getProperty(TCP_AGVMANAGER_CLIENT_SOCKET_PORT);
    }

    public String getTcpAgvManagerServerSocketPort() {
        return this.applicationProperties.getProperty(TCP_AGVMANAGER_SERVER_SERVERSOCKET_PORT);
    }

    public String getTcpAgvManagerServerDns() {
        return this.applicationProperties.getProperty(TCP_AGVMANAGER_SERVER_DNS);
    }

    public String getHTTPServerDns() {
        return this.applicationProperties.getProperty(TCP_HTTPS_SERVER_DNS);
    }

    public String getHTTPServerSocketPort() {
        return this.applicationProperties.getProperty(TCP_HTTPS_SERVER_SERVERSOCKET_PORT);
    }

    public String getHTTPSClientPort() {
        return this.applicationProperties.getProperty(TCP_HTTPS_CLIENT_SOCKET_PORT);
    }

    public String getTcpServerExecutor1() {
        return this.applicationProperties.getProperty(TCP_SERVER_EXECUTOR_1);
    }

    public String getTcpServerExecutor2() {
        return this.applicationProperties.getProperty(TCP_SERVER_EXECUTOR_2);
    }

    public String getPersistenceUnitName() {
        return this.applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    public Integer getHighCaloriesDishLimit() {
        return Integer.valueOf(this.applicationProperties.getProperty(HIGH_CALORIES_DISH_LIMIT));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Map getExtendedPersistenceProperties() {
        final Map ret = new HashMap();
        ret.put(SCHEMA_GENERATION_KEY,
                this.applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
        return ret;
    }

    public String getProperty(final String prop) {
        return this.applicationProperties.getProperty(prop);
    }
}
