package eapli.base.infrastructure.smoketests;

import eapli.base.customermanagement.application.CreateCustomerController;
import eapli.base.customermanagement.domain.*;
import eapli.base.ordermanagement.application.CreateOrderController;
import eapli.base.ordermanagement.domain.*;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.util.*;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

public class OrderSmokeTest implements Action {

    CreateOrderController controller = new CreateOrderController();
    RegisterProductController productController = new RegisterProductController();
    CreateCustomerController customerController = new CreateCustomerController();

    @Override
    public boolean execute() {

        //get todays date
        Date today = new Date();
        //create e new product
        Long CATEGORYID = 123456L;
        Code CODE = new Code("P0001");
        Description SHORT_DESCRIPTION = Description.valueOf("Test short description");
        Description EXTENDED_DESCRIPTION = Description.valueOf("Test extended description");
        Description TECHNICAL_DESCRIPTION = Description.valueOf("Test technical description");
        BrandName BRAND_NAME = BrandName.valueOf("Test brand name");
        Reference REFERENCE = Reference.valueOf("Teste reference");
        Barcode BARCODE = Barcode.valueOf(1L);
        Money PRICE = Money.valueOf("10 EUR");
        List<String> PHOTO_LIST = new ArrayList<>();
        final ProductionCode PRODUCTION_CODE = ProductionCode.valueOf("PC1");
        //Create a new a customer
        PhoneNumber phoneNumber = new PhoneNumber(123,183436789);
        BirthDate birthDate = new BirthDate(new Date("12/12/2000"));
        Gender gender = new Gender("Male");
        VAT vat = new VAT(12);
        Email email = new Email("ola44@email.com");
        String emailString = new String("ola44@email.com");
        Name name = new Name("Jose Jose");
        String userName = new String("customer1113");
        String password = new String("AAAaaa11");
        String firstName = new String("Jose");
        Address address = new Address("addressss1",14,"PostalCod5e","Portugal","Porto");

        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CLIENT_USER);

/*
        try {
            productController.registerProductWithoutProductionCode(1, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final ProductDTO productDTO = productController.getProductDTO();

        //create a new order
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(new OrderLine(Long.parseLong(productDTO.getCode()), 2, "10 EUR"));
*/
        try {
        controller.createOrderController(today, Money.valueOf(10.0, "EUR"), Weight.valueOf(15.0), null, Payment.valueOf(PaymentMethod.APPLEPAY), Shipping.valueOf(ShippingPrice.valueOf(Money.valueOf(10.0, "EUR")), ShippingType.GREEN),null );
        }catch (final IntegrityViolationException | ConcurrencyException | NumberFormatException exception){
            throw new RuntimeException(exception);
        }

        LOGGER.info("»»» Order Creation");

        return true;
    }
}