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
package eapli.base.app.backoffice.console.presentation;

import com.fasterxml.jackson.databind.ser.Serializers;
import eapli.base.app.backoffice.console.presentation.agv.CreateAGVUI;
import eapli.base.app.backoffice.console.presentation.catalog.SearchCatalogUI;
import eapli.base.app.backoffice.console.presentation.category.RegisterCategoryUI;
import eapli.base.app.backoffice.console.presentation.clientuser.CreateCustomerUI;
import eapli.base.app.backoffice.console.presentation.order.CreateOrderUI;
import eapli.base.app.backoffice.console.presentation.product.RegisterProductUI;
import eapli.base.app.backoffice.console.presentation.warehouse.ImportWarehousePlantUI;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.application.ImportWarehouseController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    // DISH TYPES
    private static final int DISH_TYPE_REGISTER_OPTION = 1;
    private static final int DISH_TYPE_LIST_OPTION = 2;
    private static final int DISH_TYPE_CHANGE_OPTION = 3;
    private static final int DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION = 4;

    // DISHES
    private static final int DISH_REGISTER_OPTION = 5;
    private static final int DISH_LIST_OPTION = 6;
    private static final int DISH_REGISTER_DTO_OPTION = 7;
    private static final int DISH_LIST_DTO_OPTION = 8;
    private static final int DISH_ACTIVATE_DEACTIVATE_OPTION = 9;
    private static final int DISH_CHANGE_OPTION = 10;

    // DISH PROPERTIES
    private static final int CHANGE_DISH_NUTRICIONAL_INFO_OPTION = 1;
    private static final int CHANGE_DISH_PRICE_OPTION = 2;

    // MATERIALS
    private static final int MATERIAL_REGISTER_OPTION = 1;
    private static final int MATERIAL_LIST_OPTION = 2;

    // REPORTING
    private static final int REPORTING_DISHES_PER_DISHTYPE_OPTION = 1;
    private static final int REPORTING_HIGH_CALORIES_DISHES_OPTION = 2;
    private static final int REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION = 3;

    // MEALS
    private static final int LIST_MEALS_OPTION = 1;
    private static final int MEAL_REGISTER_OPTION = 2;

    //CATEGORIES
    private static final int REGISTER_CATEGORY = 2;
    private static final int REGISTER_CATEGORY_MENU = 1;

    //ORDERS
    private static final int REGISTER_ORDER = 6;
    private static final int REGISTER_ORDER_MENU = 1;
    //PRODUCTS
    private static final int REGISTER_PRODUCT = 3;
    private static final int REGISTER_PRODUCT_MENU = 1;

    //CUSTOMERS
    private static final int CUSTOMER_MANAGEMENT = 4;
    private static final int CUSTOMER_MANAGEMENT_MENU = 1;

    //WAREHOUSE
    private static final int IMPORT_WAREHOUSE_PLANT = 1;
    private static final int WAREHOUSE_MANAGEMENT_MENU = 4;

    //AGV
    private static final int REGISTER_AGV = 1;
    private static final int AGV_MANAGEMENT_MENU = 5;

    //CATALOG
    private static final int CATALOG_MANAGEMENT_MENU = 5;
    private static final int CATALOG_MANAGEMENT = 1;


    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 4;


    private static final String SEPARATOR_LABEL = "--------------";


    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);

        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.SALES_CLERK)) {
            final Menu categoryMenu = buildCategoriesMenu();
            mainMenu.addSubMenu(REGISTER_CATEGORY, categoryMenu);

            final Menu productManagementMenu = buildProductMenu();
            mainMenu.addSubMenu(REGISTER_PRODUCT, productManagementMenu);

            final Menu customerManagementMenu = buildCustomerManagementMenu();
            mainMenu.addSubMenu(CUSTOMER_MANAGEMENT, customerManagementMenu);

            final Menu catalogManagementMenu = buildCatalogMenu();
            mainMenu.addSubMenu(CATALOG_MANAGEMENT_MENU, catalogManagementMenu);

            final Menu orderManagementMenu = buildOrderMenu();
            mainMenu.addSubMenu(REGISTER_ORDER, orderManagementMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.WAREHOUSE_EMPLOYEE)) {
            final Menu warehouseManagementMenu = buildWarehouseMenu();
            final Menu agvManagementMenu = buildAGV();
            mainMenu.addSubMenu(WAREHOUSE_MANAGEMENT_MENU, warehouseManagementMenu);
            mainMenu.addSubMenu(AGV_MANAGEMENT_MENU,agvManagementMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCustomerManagementMenu() {

        final Menu menu = new Menu("Customer Management >");

        menu.addItem(CUSTOMER_MANAGEMENT_MENU, "Create a new Customer!", new CreateCustomerUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildOrderMenu() {
        final Menu menusMenu = new Menu("Order Management >");

        menusMenu.addItem(REGISTER_ORDER_MENU, "Register a new Order", new CreateOrderUI()::show);
        menusMenu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menusMenu;
    }

    private Menu buildCategoriesMenu() {
        final Menu menusMenu = new Menu("Category Management >");

        menusMenu.addItem(REGISTER_CATEGORY_MENU, "Register a new category", new RegisterCategoryUI()::show);
        menusMenu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menusMenu;
    }

    private Menu buildProductMenu() {
        final Menu menu = new Menu("Product Management >");

        menu.addItem(REGISTER_PRODUCT_MENU, "Register a new product", new RegisterProductUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildAGV(){
        final Menu menu = new Menu("AGV Management >");

        menu.addItem(REGISTER_AGV,"Register new AGV",new CreateAGVUI() :: show);
        menu.addItem(EXIT_OPTION,RETURN_LABEL,Actions.SUCCESS);

        return menu;
    }

    private Menu buildWarehouseMenu() {
        final Menu menu = new Menu("Warehouse Management >");
        Logger logger = LoggerFactory.getLogger(MainMenu.class);
        try {
            ImportWarehouseController importWarehouseController = new ImportWarehouseController();
            importWarehouseController.startup();
        } catch (IOException e) {
            logger.error("Error importing warehouse", e);
            System.out.println("WARNING!!! Error importing warehouse, please check the file");
        } catch (ParseException e) {
            System.out.println("WARNING!!! Error reading the warehouse plant, please check the file");
        }

        menu.addItem(IMPORT_WAREHOUSE_PLANT, "Import Warehouse Plant", new ImportWarehousePlantUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCatalogMenu() {
        final Menu menu = new Menu("Catalog Management >");

        menu.addItem(CATALOG_MANAGEMENT, "Search the products catalog", new SearchCatalogUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }
}
