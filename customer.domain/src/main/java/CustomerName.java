import javax.persistence.Embeddable;

@Embeddable
public class CustomerName {

    private String customerName;

    public CustomerName(final String customerName){
        this.customerName = customerName;
    }

    public CustomerName() {

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFirstName(String customerName){

        String split[] = customerName.split(" ");
        return split[0];
    }

    public String getLastName(String customerName){

        String split[] = customerName.split(" ");
        return split[split.length];
    }
}
