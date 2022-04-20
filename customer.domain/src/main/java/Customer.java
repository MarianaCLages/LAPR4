import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Customer  {

    @Id
    private CustomerId customerId;

    @Embedded
    private PhoneNumber customerPhoneNumber;

    private Date customerBirthDate;
    private String customerName;
    private String customerGender;
    private Long customerVAT;
    private String customerEmail;


    public Customer(final PhoneNumber customerPhoneNumber,final Date customerBirthDate, final String customerName,final String customerGender, final Long customerVAT,String customerEmail) throws IllegalAccessException {


        this.customerBirthDate = customerBirthDate;
        this.customerName = customerName;


        this.customerPhoneNumber = customerPhoneNumber;
        this.customerGender = customerGender;
        this.customerVAT = customerVAT;
        this.customerEmail = customerEmail;
    }

    public Customer() {

    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }



    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }



    public String getFirstName(){

        String split [] = customerName.split(" ");
        return  split[0];
    }

    public String getLastName(){

        String split [] = customerName.split(" ");
        return split[split.length];
    }
}
