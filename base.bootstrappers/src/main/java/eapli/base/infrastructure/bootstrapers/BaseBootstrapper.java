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

import eapli.base.binmanagement.domain.Bin;
import eapli.base.binmanagement.domain.BinBuilder;
import eapli.base.binmanagement.domain.BinLocation;
import eapli.base.binmanagement.repositories.BinRepository;
import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.domain.CategoryBuilder;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.base.customermanagement.domain.*;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Photo;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductBuilder;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.surveymanagement.domain.Rule;
import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.domain.SurveyBuilder;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.base.surveymanagement.repositories.SurveyRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.base.warehousemanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.Location;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseBuilder;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Base Bootstrapping data app
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class BaseBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseBootstrapper.class);

    private static final String POWERUSER_A1 = "poweruserA1";
    private static final String POWERUSER = "poweruser";

    private static final String SALES_CLERK_PASS = "Sales123456";
    private static final String SALES_CLERK_ID = "salesclerk";

    private static final String WAREHOUSE_EMPLOYEE = "warehouseemp";

    private static final String WAREHOUSE_EMPLOYEE_PASS = "Warehouse123";

    private static final String SALES_MANAGER_ID = "salesmanager";
    private static final String SALES_MANAGER_PASS = "Manager123";


    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final ClientRepository clientRepository = PersistenceContext.repositories().client();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();
    private final BinRepository binRepository = PersistenceContext.repositories().bins();
    private final SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();

    /* private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();*/

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = {new MasterUsersBootstrapper(),};

        registerPowerUser();
        registerWarehouseEmployee();
        authenticateForBootstrapping();
        registerClient();
        registerCategory();
        registerProduct();
        registerBin();
        registerOrder();
        registerSalesClerk();
        registerSalesManager();
        createSurvey();

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
        final Customer customer = new CustomerBuilder().brithDate(new BirthDate(new Date("12/12/2002"))).vat(new VAT(12)).number(new PhoneNumber(123, 123456789)).named(new Name("customer customer")).gender(new Gender("Male")).email(new Email("email@email.com")).address(new Address("Billing Address", 11, "postal code", "city", "country")).build();


        try {
            clientRepository.save(customer);
            return true;
        } catch (IllegalArgumentException ex) {
            LOGGER.warn("Customer Failed");
            return false;
        }
    }

    private boolean registerProduct() {
        try {
            List<Photo> photoList = new ArrayList<>();

            final Product product1 = new ProductBuilder().withACategoryId(4L)
                    .coded(Code.valueOf("P0001"))
                    .withAShortDescription("Short description")
                    .withAnExtendedDescription("Extended description")
                    .withATechnicalDescription("Technical description")
                    .withABrandName("Brand name")
                    .withAReference("Reference")
                    .withABarcode(123456789L)
                    .withAPrice(Money.euros(30))
                    .withASetOfPhotos(photoList).build();

            final Product product2 = new ProductBuilder().withACategoryId(4L)
                    .coded(Code.valueOf("P0002"))
                    .withAShortDescription("Tiny description")
                    .withAnExtendedDescription("Enormous description")
                    .withATechnicalDescription("Tech description")
                    .withABrandName("IKEA")
                    .withAReference("Ref")
                    .withABarcode(987654321L)
                    .withAPrice(Money.euros(15))
                    .withASetOfPhotos(photoList)
                    .withAProductionCode("PC001").build();

            productRepository.save(product1);
            productRepository.save(product2);
            return true;
        } catch (IllegalArgumentException ex) {
            LOGGER.warn("Product Failed");
            return false;
        }
    }

    private boolean registerBin() {
        final BinBuilder binBuilder = new BinBuilder().withABinLocation(BinLocation.valueOf(1, 1, 1)).withAProductId(1L);

        final Bin bin = binBuilder.build();

        try {
            binRepository.save(bin);
            return true;
        } catch (IllegalArgumentException ex) {
            LOGGER.warn("Bin Failed");
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

    private boolean createSurvey() {
        try {
            byte[] quest = new byte[0];
            List<Rule> rules = new ArrayList<>();

            final Survey survey = new SurveyBuilder()
                    .withASurveyCode(SurveyCode.valueOf("COSM22-01"))
                    .withADescription(Description.valueOf("Survey description"))
                    .withAPeriod(14)
                    .withAQuestionnaire(quest)
                    .withASetOfRules(rules).build();

            surveyRepository.save(survey);
            return true;

        } catch (IllegalArgumentException ex) {
            LOGGER.warn("Survey Failed");
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
        userBuilder.withUsername(SALES_CLERK_ID).withPassword(SALES_CLERK_PASS).withName("Tiago", "Ferreira").withEmail("email123@email.org").withRoles(BaseRoles.SALES_CLERK);
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


    private boolean registerOrder() {

        final ProductRepository productRepository = PersistenceContext.repositories().products();
        final ClientRepository clientRepository = PersistenceContext.repositories().client();

        List<OrderLine> orderLineList = new ArrayList<>();
        Product product = productRepository.findByCode(new Code("P0001")).get(0);
        Customer customer = clientRepository.findByEmail(new Email("email@email.com"));
        OrderLine orderLine = new OrderLine(product.identity(), 12, "12");
        orderLineList.add(orderLine);

        List<OrderLine> orderLineList1 = new ArrayList<>();
        Product product1 = productRepository.findByCode(new Code("P0001")).get(0);
        OrderLine orderLine1 = new OrderLine(product.identity(), 12, "12");
        orderLineList1.add(orderLine1);

        Date date = new Date("12/01/2020");
        Date date2 = new Date("15/03/2021");
        Date date3 = new Date("20/01/2021");
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar2.setTime(date2);
        calendar3.setTime(date3);


        final ClientOrder clientOrder = new OrderBuilder()
                .addDate(new OrderDate())
                .addDate(calendar1)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer)
                .addOrderLine(orderLineList)
                .addState(OrderState.TO_BE_PREPARED)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        final ClientOrder clientOrder1 = new OrderBuilder()
                .addDate(calendar2)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer)
                .addOrderLine(orderLineList1)
                .addState(OrderState.TO_BE_PREPARED)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        final ClientOrder clientOrder2 = new OrderBuilder()
                .addDate(calendar3)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer)
                .addOrderLine(orderLineList1)
                .addState(OrderState.TO_BE_PREPARED)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        try {

            orderRepository.save(clientOrder);
            orderRepository.save(clientOrder1);
            orderRepository.save(clientOrder2);
            return true;
        } catch (IllegalArgumentException e) {
            LOGGER.error("Order failed!");
            return false;
        }


    }

    private boolean registerWarehouseEmployee() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();

        userBuilder.withUsername(WAREHOUSE_EMPLOYEE).withPassword(WAREHOUSE_EMPLOYEE_PASS).withName("Miguel", "Jordan").withEmail("emaildojordans@lei.org").withRoles(BaseRoles.WAREHOUSE_EMPLOYEE);

        final SystemUser newUser = userBuilder.build();
        SystemUser warehouseEmployee;
        try {
            warehouseEmployee = userRepository.save(newUser);
            assert warehouseEmployee != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerSalesManager() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(SALES_MANAGER_ID).withPassword(SALES_MANAGER_PASS).withName("Mariana", "Lages").withEmail("salesmanager@email.pt").withRoles(BaseRoles.SALES_MANAGER);
        final SystemUser newUser = userBuilder.build();

        SystemUser salesManager;
        try {
            salesManager = userRepository.save(newUser);
            assert salesManager != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    /*    private boolean registerAGV(){


            final AGV agv = new AGVBuilder()
                    .identifier("aaaaaaaa")
                    .autonomy(20)
                    .description("aaaaaa")
                    .dock(null)
                    .status(AGVStatus.AVAILABLE)
                    .build();

            try {
                agvRepository.save(agv);
                return true;
            }catch (IllegalArgumentException ex){
                LOGGER.warn("AGV Failed");
                return false;
            }

        }*/
    private boolean registerWarehouse() {
        final WarehouseBuilder warehouseBuilder = new WarehouseBuilder().withLength(20).withWidth(30).withSquare(1).withUnit("m").addAgvDock(String.valueOf(1), new Location(5, 4), new Location(5, 5), new Location(6, 6), Accessibility.LENGHT_PLUS).addAgvDock(String.valueOf(2), new Location(10, 4), new Location(10, 5), new Location(10, 6), Accessibility.WIDTH_MINUS).addAisle(1, new Location(0, 1), new Location(0, 6), new Location(3, 3), Accessibility.LENGHT_PLUS).addAisle(2, new Location(10, 15), new Location(10, 20), new Location(15, 15), Accessibility.WIDTH_MINUS).addRow(1, 1, new Location(0, 1), new Location(0, 2), 5).addRow(1, 2, new Location(0, 2), new Location(0, 3), 10).addRow(2, 1, new Location(10, 15), new Location(10, 16), 5).withName("A Simple Warehouse");

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
