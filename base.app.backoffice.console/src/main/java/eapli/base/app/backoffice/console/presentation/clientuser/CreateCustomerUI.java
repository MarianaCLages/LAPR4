package eapli.base.app.backoffice.console.presentation.clientuser;

import eapli.base.customermanagement.application.CreateCustomerController;
import eapli.base.customermanagement.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


public class CreateCustomerUI extends AbstractUI {

    private final CreateCustomerController createCustomerController = new CreateCustomerController();

    @Override
    protected boolean doShow() {

        int option = Console.readInteger("1. Read from File   2.Manually input the Customer");

        if(option == 2) {

            try {
                String email = Console.readLine("What is your email?");
                Email customerEmail = new Email(email);
                String userName = Console.readLine("New username username:");
                String password = Console.readLine("New password:");

                Name name = new Name(new String(Console.readLine("What is your name?")));
                String firstName = Console.readLine("What is your first name?");
                String lastName = Console.readLine("What is your last name?");
                VAT VAT = new VAT(Console.readInteger("What is your VAT?"));
                Gender gender = new Gender(Console.readLine("What is your gender?"));
                BirthDate birthDate = new BirthDate(Console.readDate("When is your birthday?"));
                PhoneNumber phoneNumber = new PhoneNumber(Console.readInteger("What is your phone indicatives?"), Console.readLong("What is your phone number?"));
                final Set<Role> roles = new HashSet<>();
                roles.add(BaseRoles.CLIENT_USER);

                createCustomerController.registerCustomer(phoneNumber, birthDate, name, gender, VAT, customerEmail, userName, password, firstName, lastName, email, roles, Calendar.getInstance());
            } catch (IllegalArgumentException ex) {

                if (ex.getMessage() != null) {
                    System.out.println(ex.getMessage());
                } else {

                    System.out.println("Incorrect Password Format! Please input at least an UpperCase letter and a number!");
                    createCustomerController.deleteCustomer();
                }
                return false;

            }
        }
        else{

            try {
                String path = Console.readLine("Path for the customer and user file (must be in the same path):");
                createCustomerController.createCustomerByFile(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }

        }
        System.out.println("Customer Registered with success!");

        return true;
    }

    @Override
    public String headline() {
        return "Create new Customer";
    }
}
