package eapli.base.app.backoffice.console.presentation.clientuser;

import eapli.base.app.backoffice.console.presentation.category.RegisterCategoryUI;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCustomerUI extends AbstractUI {

    private final CreateCustomerController createCustomerController = new CreateCustomerController();
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCategoryUI.class);

    @Override
    protected boolean doShow() {

        int option = Console.readInteger("1. Manually input the Customer   2.Import Customer(s) from a file");

        if (option == 1) {

            try {


                //Value Objects
                Gender gender = null;
                boolean userPassVerify = false;
                Email customerEmail = null;
                String userName = null;
                String password = null;
                String email = null;

                //Verifiers
                boolean verifyGender = false;
                boolean verifyEmail = false;

                do {
                    try {
                        email = Console.readLine("Customer email:");
                        customerEmail = new Email(email);

                        verifyEmail = true;

                    } catch (IllegalArgumentException ex) {
                        LOGGER.error(ex.getMessage());
                    }

                } while (!verifyEmail);


                do {
                    try {
                        userName = Console.readLine("New username:");

                        if (userName.isEmpty()) {
                            throw new IllegalArgumentException("Please do not input an empty username!");
                        }
                        password = Console.readLine("New password:");
                        if (password.isEmpty()) {
                            throw new IllegalArgumentException("Please do not input an password!");
                        }

                    } catch (IllegalArgumentException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                } while (userName.isEmpty()|| password.isEmpty());


                Name name = new Name(Console.readLine("What is the Customer name?"));
                String firstName = Console.readLine("What is the Customer first name?");
                String lastName = Console.readLine("What is the Customer last name?");
                VAT VAT = new VAT(Console.readInteger("What is the Customer VAT?"));

                Address address = new Address(Console.readLine("Billing Address?"), Console.readInteger("Customer Door Number?"),
                        Console.readLine("Customer Postal Code?"),Console.readLine("Customer city?"),
                        Console.readLine("Customer country?"));

                do {
                    try {
                        gender = new Gender(Console.readLine("What is the Customer gender?"));
                        verifyGender = true;
                    } catch (IllegalArgumentException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                } while (!verifyGender);



                BirthDate birthDate = new BirthDate(Console.readDate("When is the Customer birthday?"));
                PhoneNumber phoneNumber = new PhoneNumber(Console.readInteger("What is the Customer phone indicatives?"), Console.readLong("What is your phone number?"));
                final Set<Role> roles = new HashSet<>();
                roles.add(BaseRoles.CLIENT_USER);

                createCustomerController.registerCustomer(phoneNumber, birthDate, name, gender, VAT, customerEmail, userName, password, firstName, lastName, email, roles, Calendar.getInstance(), address);
            } catch (IllegalArgumentException ex) {

                if (ex.getMessage() != null) {
                    LOGGER.error(ex.getMessage());
                } else {

                    System.out.println("Incorrect Password Format! Please input at least an UpperCase letter and a number!");
                    createCustomerController.deleteCustomer();
                }
                return false;

            }
            System.out.println("Customer Registered with success!");
        } else if (option == 2) {

            try {
                String path = Console.readLine("Path for the customer and user file (must be in the same path):");
                createCustomerController.createCustomerByFile(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            catch (ArrayIndexOutOfBoundsException ex){
                LOGGER.error(ex.getMessage());
                return false;
            }

            System.out.println("Customer Registered with success!");
        } else {
            System.out.println("Registration Cancelled");
        }


        return true;
    }

    @Override
    public String headline() {
        return "Create new Customer";
    }
}
