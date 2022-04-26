/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.infrastructure.bootstrapers;

import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.domain.CategoryBuilder;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.base.customermanagement.domain.*;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.warehousemanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.Location;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseBuilder;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;

import java.util.Date;

/**
 * Base Bootstrapping data app
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class BaseBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            BaseBootstrapper.class);

    private static final String POWERUSER_A1 = "poweruserA1";
    private static final String POWERUSER = "poweruser";

    private static final String SALES_CLERK_PASS = "Sales123456";
    private static final String SALES_CLERK_ID = "salesclerk";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final ClientRepository clientRepository = PersistenceContext.repositories().createClient();
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = {new MasterUsersBootstrapper(),};

        registerPowerUser();
        authenticateForBootstrapping();
        registerClient();
        registerCategory();
       // registerWarehouse();
        registerSalesClerk();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    /**
     * register a power user directly in the persistence layer as we need to
     * circumvent authorisations in the Application Layer
     */
    private boolean registerClient() {
        final Customer customer = new CustomerBuilder().brithDate(new BirthDate(new Date("12/12/2002")))
                .vat(new VAT(12)).number(new PhoneNumber(123,123456789))
                .named(new Name("customer customer")).gender(new Gender("Male"))
                .email(new Email("email@email.com")).address(new Address("Billing Address",11,"postal code","city","country")).build();


        try {
            clientRepository.save(customer);
            return true;
        } catch (IllegalArgumentException ex) {
            LOGGER.warn("Customer Failed");
            return false;
        }
    }

    private boolean registerCategory() {
        final Category category = new CategoryBuilder().coded(AlphaNumericCode.valueOf("A0001")).withADescription("Test").build();

        try {
            categoryRepository.save(category);
            return true;
        } catch (IllegalArgumentException ex) {
            LOGGER.warn("Category Failed");
            return false;
        }
    }

    private boolean registerPowerUser() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(POWERUSER).withPassword(POWERUSER_A1).withName("joe", "power")
                .withEmail("joe@email.org").withRoles(BaseRoles.POWER_USER);
        final SystemUser newUser = userBuilder.build();

        SystemUser poweruser;
        try {
            poweruser = userRepository.save(newUser);
            assert poweruser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerSalesClerk() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(SALES_CLERK_ID).withPassword(SALES_CLERK_PASS).withName("Tiago", "Ferreira")
                .withEmail("email123@email.org").withRoles(BaseRoles.SALES_CLERK);
        final SystemUser newUser = userBuilder.build();

        SystemUser salesClerk;
        try {
            salesClerk = userRepository.save(newUser);
            assert salesClerk != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerWarehouse() {
        final WarehouseBuilder warehouseBuilder = new WarehouseBuilder()
                .withLength(20)
                .withWidth(30)
                .withSquare(1)
                .withUnit("m")
                .addAgvDock(String.valueOf(1), new Location(5, 4), new Location(5, 5), new Location(6, 6), Accessibility.LENGHT_PLUS)
                .addAgvDock(String.valueOf(2), new Location(10, 4), new Location(10, 5), new Location(10, 6), Accessibility.WIDTH_MINUS)
                .addAisle(1, new Location(0, 1), new Location(0, 6), new Location(3, 3), Accessibility.LENGHT_PLUS)
                .addAisle(2, new Location(10, 15), new Location(10, 20), new Location(15, 15), Accessibility.WIDTH_MINUS)
                .addRow(1, 1, new Location(0, 1), new Location(0, 2), 5)
                .addRow(1, 2, new Location(0, 2), new Location(0, 3), 10)
                .addRow(2, 1, new Location(10, 15), new Location(10, 16), 5)
                .withName("A Simple Warehouse");

        final Warehouse warehouse = warehouseBuilder.build();
        try {
            warehouseRepository.save(warehouse);
            return true;
        } catch (IllegalArgumentException ex) {
            LOGGER.warn("Warehouse Failed");
            return false;
        }
    }

    /**
     * authenticate a super user to be able to register new users
     */
    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(POWERUSER, POWERUSER_A1);
        Invariants.ensure(authz.hasSession());
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
