package eapli.base.app.backoffice.console.presentation.clientuser;

import eapli.base.customermanagement.application.CreateCustomerController;
import eapli.base.customermanagement.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;
import org.springframework.security.core.parameters.P;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


public class CreateCustomerUI extends AbstractUI {

    private final CreateCustomerController createCustomerController = new CreateCustomerController();

    @Override
    protected boolean doShow() {



        try{
            String email = Console.readLine("What is your email?");
            CustomerEmail customerEmail = new CustomerEmail(email);
            String userName = Console.readLine("New username username:");
            String password = Console.readLine("New password:");

            CustomerName customerName = new CustomerName(new String(Console.readLine("What is your name?")));
            String firstName = Console.readLine("What is your first name?");
            String lastName = Console.readLine("What is your last name?");
            CustomerVAT customerVAT = new CustomerVAT(Console.readInteger("What is your VAT?"));
            CustomerGender customerGender = new CustomerGender(Console.readLine("What is your gender?"));
            CustomerBirthDate customerBirthDate = new CustomerBirthDate(Console.readDate("When is your birthday?"));
            PhoneNumber phoneNumber = new PhoneNumber(Console.readInteger("What is your phone indicatives?"),Console.readLong("What is your phone number?"));
            final Set<Role> roles = new HashSet<>();
            roles.add(BaseRoles.CLIENT_USER);

            createCustomerController.registerCustomer(phoneNumber,customerBirthDate,customerName,customerGender,customerVAT,customerEmail,userName,password,firstName,lastName,email,roles,Calendar.getInstance());
        }catch (IllegalArgumentException ex){
            ex.getMessage();
            return false;

        }

        System.out.println("Customer Registered with success!");
        return true;
    }

    @Override
    public String headline() {
        return "Create new Customer";
    }
}
