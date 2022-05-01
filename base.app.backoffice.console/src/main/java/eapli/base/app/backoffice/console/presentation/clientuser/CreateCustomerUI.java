package eapli.base.app.backoffice.console.presentation.clientuser;

import eapli.base.customermanagement.application.CreateCustomerController;
import eapli.base.customermanagement.application.SearchCustomerService;
import eapli.base.customermanagement.domain.*;
import eapli.base.customermanagement.dto.CustomerDTO;
import eapli.base.productmanagement.application.SearchProductService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CreateCustomerUI extends AbstractUI {

    private final CreateCustomerController createCustomerController = new CreateCustomerController();

    @Override
    protected boolean doShow() {

        boolean checkOptionValue = false;
        int option = 0;

        do {
            try {
                option = Console.readInteger("1. Manually input the Customer   2.Import Customer(s) from a file\n");

                if (option < 1 || option > 2) {
                    throw new IllegalArgumentException("\nPlease enter a valid option! (1/2)");
                }
                checkOptionValue = true;
            } catch (IllegalArgumentException e) {
                checkOptionValue = false;
                System.out.println(e.getMessage());
            }

        } while (!checkOptionValue);

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
                        email = Console.readLine("\nPlease enter the customer email: ");
                        customerEmail = new Email(email);

                        verifyEmail = true;
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }

                } while (!verifyEmail);

                do {
                    try {
                        userName = Console.readLine("\nPlease enter the customer username: ");

                        if (userName.isEmpty()) {
                            throw new IllegalArgumentException("\nPlease do not input an empty username! ");
                        }

                        password = Console.readLine("\nPlease enter the user password: (Please input at least an UpperCase letter and a number!)");



                        if (password.isEmpty()) {
                            throw new IllegalArgumentException("\nPlease do not input an empty password! ");
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } while (Objects.requireNonNull(userName).isEmpty() || Objects.requireNonNull(password).isEmpty());


                checkOptionValue = true;
                String firstName = null;

                do {
                    try {

                        firstName = Console.readLine("\nPlease specify the customer first name: ");

                        if (firstName.isEmpty()) {
                            throw new IllegalArgumentException("\nPlease enter a valid customer first name (The input cannot be empty)!");
                        }
                        checkOptionValue = true;

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        checkOptionValue = false;
                    }

                } while (!checkOptionValue);


                checkOptionValue = true;
                String lastName = null;

                do {
                    try {
                        lastName = Console.readLine("\nPlease specify the customer last name: ");

                        if (lastName.isEmpty()) {
                            throw new IllegalArgumentException("\nPlease enter a valid customer last name (The input cannot be empty)!");
                        }
                        checkOptionValue = true;

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        checkOptionValue = false;
                    }
                } while (!checkOptionValue);


                VAT vat = null;
                checkOptionValue = true;

                do {
                    try {
                        int vatInt = (Console.readInteger("\nPlease specify the customer VAT: "));
                        checkOptionValue = true;

                        if (vatInt < 0) {
                            throw new NumberFormatException("\nPlease enter a positive number! There cannot be a negative VAT number!");
                        }

                        vat = new VAT(vatInt);
                    } catch (Exception e) {
                        System.out.println("\nPlease only enter numbers! (No chars beside numbers are allowed!)");
                        checkOptionValue = false;
                    }
                } while (!checkOptionValue);


                checkOptionValue = true;
                Address address = null;

                do {
                    try {
                        address = new Address(Console.readLine("\nPlease specify the customer billing address: "), Console.readInteger("\nPlease specify the customer door number: (Notice: The input must be a number!)"),
                                Console.readLine("\nPlease specify the customer postal code: "), Console.readLine("\nPlease specify the customer city: "),
                                Console.readLine("\nPlease specify the customer country: "));

                        checkOptionValue = true;

                    } catch (IllegalArgumentException e) {
                        checkOptionValue = false;
                    }
                } while (!checkOptionValue);


                do {
                    try {
                        gender = new Gender(Console.readLine("\nPlease specify the customer gender: (Notice: Right now the system only accepts male/female/other)"));
                        verifyGender = true;
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }
                } while (!verifyGender);

                BirthDate birthDate = null;
                checkOptionValue = true;

                do {
                    try {
                        birthDate = new BirthDate(Console.readDate("\nPlease specify the customer birthday: (NOTICE: The acceptable format is: DAY/MONTH/YEAR, all other formats won't work!"));
                        checkOptionValue = true;

                    } catch (Exception e) {
                        System.out.println("\nPlease enter a valid format! (Reminder DAY/MONTH/YEAR)");
                        checkOptionValue = false;
                    }
                } while (!checkOptionValue);


                checkOptionValue = true;
                PhoneNumber phoneNumber = null;

                do {
                    try {
                        phoneNumber = new PhoneNumber(Console.readInteger("\nPlease specify the customer phone indicatives: "), Console.readLong("\nPlease specify the customer phone number: "));
                        checkOptionValue = true;

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        checkOptionValue = false;
                    }
                } while (!checkOptionValue);


                final Set<Role> roles = new HashSet<>();
                roles.add(BaseRoles.CLIENT_USER);

                Name name = new Name((firstName + lastName));
                CustomerDTO customerDTO = createCustomerController.registerCustomer(phoneNumber, birthDate, name, gender, vat, customerEmail, userName, password, firstName, lastName, email, roles, Calendar.getInstance(), address).toDTO();

                System.out.println("\n\n" + customerDTO);
            } catch (IllegalArgumentException ex) {

                if (ex.getMessage() != null) {
                    System.out.println(ex.getMessage());
                } else {

                    System.out.println("\nIncorrect Password Format! Please input at least an UpperCase letter and a number!\n\nOperation failed!\n");
                    createCustomerController.deleteCustomer();
                }
                return false;

            }
            System.out.println("\n\nOperation success!\n");
        } else if (option == 2) {

            try {
                String path = Console.readLine("\nPath for the customer and user file (must be in the same path):");
                createCustomerController.createCustomerByFile(path);
            } catch (FileNotFoundException e) {
                System.out.println("\nThe file wasn't found! Please try again with another file!");
                return false;
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
                return false;
            }

            System.out.println("\n\nOperation Success!!\n");
        } else {
            System.out.println("\nRegistration Cancelled");
            System.out.println("\n\nOperation Failed!");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create new Customer";
    }
}

