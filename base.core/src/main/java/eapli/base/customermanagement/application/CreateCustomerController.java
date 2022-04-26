package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@UseCaseController
public class CreateCustomerController {

        private final AuthorizationService authz = AuthzRegistry.authorizationService();
        private final CreateCustomerService createCustomerService = new CreateCustomerService();
        private final CreateUserService createUserService = new CreateUserService();
        private Customer customer = null;
        private final CreateCustomerByFileService createCustomerByFileService = new CreateCustomerByFileService();


        public Customer registerCustomer(final PhoneNumber customerPhoneNumber, final BirthDate birthDate,
                                         final Name name, final Gender gender,
                                         final VAT VAT, final Email customerEmail,
                                         final String username, final String password, final String firstName,
                                         final String lastName, final String email,
                                         final Set<Role> roles, final Calendar createdOn,
                                         final Address address){


                authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);


                customer = createCustomerService.registerCustomer(customerPhoneNumber, birthDate, name, gender, VAT,customerEmail,address);
                createUserService.createUser(username,password,firstName,lastName,email,roles,createdOn);


                return customer;
        }

        public boolean deleteCustomer(){
                final DeleteCustomerService deleteCustomerService = new DeleteCustomerService();
                deleteCustomerService.deleteCustomer(customer);
                return true;
        }

        public boolean createCustomerByFile(String path) throws FileNotFoundException {

                final Set<Role> roles = new HashSet<>();
                roles.add(BaseRoles.CLIENT_USER);
                List<String []> customerList = createCustomerByFileService.createCustomerByFile(new File(path));

                for(String [] customerArray : customerList){

                        createCustomerService.registerCustomer(new PhoneNumber(Integer.valueOf(customerArray[5]),Long.valueOf(customerArray[6]))
                        ,new BirthDate(new Date(customerArray[3])),new Name(customerArray[1])
                        ,new Gender(customerArray[2]),new VAT(Integer.valueOf(customerArray[4]))
                        ,new Email(customerArray[0]),new Address(customerArray[11],Integer.valueOf(customerArray[12]),customerArray[13],customerArray[14],customerArray[15]));
                        createUserService.createUser(customerArray[7],customerArray[8],customerArray[9]
                        ,customerArray[10],customerArray[0],roles,Calendar.getInstance());
                }

                return true;
        }

}


