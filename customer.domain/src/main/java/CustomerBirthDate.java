import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class CustomerBirthDate {


    private Date customerDate;


    public CustomerBirthDate(Date customerDate){
        this.customerDate = customerDate;
    }

    public CustomerBirthDate() {

    }

    public Date getCustomerDate() {
        return customerDate;
    }

    public void setCustomerDate(Date customerDate) {
        this.customerDate = customerDate;
    }
}
