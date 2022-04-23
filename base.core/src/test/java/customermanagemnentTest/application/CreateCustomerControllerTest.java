package customermanagemnentTest.application;

import eapli.base.customermanagement.application.CreateCustomerController;
import eapli.base.customermanagement.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import static org.junit.Assert.*;

public class CreateCustomerControllerTest {

    private CreateCustomerController createCustomerController = new CreateCustomerController();

    private CustomerBirthDate customerBirthDate1 = new CustomerBirthDate(new Date("11/1/2001"));
    private CustomerEmail customerEmail1 = new CustomerEmail("ola@gmail.com");
    private CustomerGender customerGender1 = new CustomerGender("Male");
    private CustomerName customerName1 = new CustomerName("Tiago Ferreira");
    private CustomerVAT customerVAT1 = new CustomerVAT(2);
    private PhoneNumber phoneNumber1 = new PhoneNumber(123,123456789);
    private String username = "olaaaaa";
    private String password = "asdasdsa";
    private String firstName = "primeiro";
    private String lastName = "ultimo";
    private String email = "email@email.com";
    private Set<Role> role;


/*
    @Test
    public void registerCustomer(){

        role.add(BaseRoles.CLIENT_USER);
        Customer customer = createCustomerController.registerCustomer(phoneNumber1,customerBirthDate1,customerName1,customerGender1,customerVAT1,customerEmail1,username,password,firstName,lastName,email,role, Calendar.getInstance());

        assertNotEquals(null,customer);
    }*/
}
