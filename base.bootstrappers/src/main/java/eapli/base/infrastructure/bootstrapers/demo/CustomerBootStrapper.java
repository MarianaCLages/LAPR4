package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.customermanagement.application.CreateCustomerController;
import eapli.base.customermanagement.domain.*;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerBootStrapper implements Action {


    private static final Logger LOGGER = LogManager.getLogger(ProductBootstrapper.class);
    private final CreateCustomerController controller = new CreateCustomerController();


    @Override
    public boolean execute() {

        final PhoneNumber phoneNumber = new PhoneNumber(123,123436789);
        final PhoneNumber phoneNumber2 = new PhoneNumber(123,123436789);
        final PhoneNumber phoneNumber3 = new PhoneNumber(123,123436789);

        final BirthDate birthDate = new BirthDate(new Date("12/12/2000"));
        final BirthDate birthDate2 = new BirthDate(new Date("13/12/2000"));
        final BirthDate birthDate3 = new BirthDate(new Date("14/12/2000"));

        final Gender gender = new Gender("Male");
        final Gender gender2 = new Gender("Female");
        final Gender gender3 = new Gender("Other");

        final VAT vat = new VAT(1);
        final VAT vat2 = new VAT(2);
        final VAT vat3 = new VAT(3);

        final Email email = new Email("ola@email.com");
        final Email email2 = new Email("hello@email.com");
        final Email email3 = new Email("bonjour@email.com");

        final String emailString = new String("ola@email.com");
        final String emailString2 = new String("hello@email.com");
        final String emailString3 = new String("bonjour@email.com");

        final Name name = new Name("Ambrosio dos Ambrosios");
        final Name name2 = new Name("Amelia das Amelias");
        final Name name3 = new Name("Outros dos Outros");

        final String userName = new String("customer13");
        final String userName2 = new String("customer14");
        final String userName3 = new String("customer15");

        final String password = new String("Password13");
        final String password2 = new String("Password14");
        final String password3 = new String("Password15");

        final String firstName = new String("Ambrosio");
        final String firstName2 = new String("Amelia");
        final String firstName3 = new String("Outro");

        final Address address = new Address("address1",14,"PostalCode","Portugal","Porto");
        final Address address2 = new Address("address2",13,"PostalCode2","Portugal","Porto");
        final Address address3 = new Address("address3",15,"PostalCode3","Portugal","Porto");

        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CLIENT_USER);

        registerCustomer(phoneNumber,birthDate,name,gender,vat,email,userName,password,firstName,firstName,emailString,roles,Calendar.getInstance(),address);
        registerCustomer(phoneNumber2,birthDate2,name2,gender2,vat2,email2,userName2,password2,firstName2,firstName2,emailString2,roles,Calendar.getInstance(),address2);
        registerCustomer(phoneNumber3,birthDate3,name3,gender3,vat3,email3,userName3,password3,firstName3,firstName3,emailString3,roles,Calendar.getInstance(),address3);



        return true;
    }


    private void registerCustomer(final PhoneNumber customerPhoneNumber, final BirthDate birthDate,
                                  final Name name, final Gender gender,
                                  final VAT VAT, final Email customerEmail,
                                  final String username, final String password, final String firstName,
                                  final String lastName, final String email,
                                  final Set<Role> roles, final Calendar createdOn,
                                  final Address address){
        try{

            Customer customer = controller.registerCustomer(customerPhoneNumber,birthDate,name,gender,VAT,customerEmail,username,password,firstName,firstName,email,roles,createdOn,address);

        }catch (final IntegrityViolationException | ConcurrencyException exception){
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "Customer");
            LOGGER.trace("Assuming existing record", exception);
        }

    }
}

