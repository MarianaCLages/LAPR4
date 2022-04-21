import javax.persistence.Embeddable;

@Embeddable
public class CustomerEmail {

    private String customerEmail;

    public  CustomerEmail(final String customerEmail) throws IllegalAccessException {
        checkEmail(customerEmail);
        this.customerEmail = customerEmail;
    }

    public CustomerEmail() {

    }

    public void checkEmail(String customerEmail) throws IllegalAccessException {

        if(!customerEmail.contains("@") && !customerEmail.endsWith(".com") && customerEmail.charAt(0) == '@'){
            throw new IllegalAccessException("Incorrect indicative format!");
        }
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
