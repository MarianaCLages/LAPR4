package eapli.base.infrastructure.smoketests;
import eapli.base.customermanagement.application.CreateCustomerController;
import eapli.base.customermanagement.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerSmokeTest implements Action{


    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CustomerSmokeTest.class);

    @Override
    public boolean execute() {
        checkASimpleCustomer();
        return false;
    }

    private void checkASimpleCustomer(){

        CreateCustomerController controller = new CreateCustomerController();

        final PhoneNumber phoneNumber = new PhoneNumber(123,183436789);
        final BirthDate birthDate = new BirthDate(new Date("12/12/2000"));
        final Gender gender = new Gender("Male");
        final VAT vat = new VAT(12);
        final Email email = new Email("ola44@email.com");
        final String emailString = new String("ola44@email.com");
        final Name name = new Name("Jose Jose");
        final String userName = new String("customer1113");
        final String password = new String("AAAaaa11");
        final String firstName = new String("Jose");
        final Address address = new Address("addressss1",14,"PostalCod5e","Portugal","Porto");

        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CLIENT_USER);

        try {
            controller.registerCustomer(phoneNumber, birthDate, name, gender, vat, email, userName, password, firstName, firstName, emailString, roles, Calendar.getInstance(), address);
        }catch (final IntegrityViolationException | ConcurrencyException exception){
            throw new RuntimeException(exception);
        }

        LOGGER.info("»»» Customer Creation");

    }
}
