import javax.persistence.Embeddable;

@Embeddable
public class CustomerVAT {

    private int customerVAT;

    public CustomerVAT(final int customerVAT){
        this.customerVAT = customerVAT;
    }

    public CustomerVAT() {

    }

    public int getCustomerVAT() {
        return customerVAT;
    }

    public void setCustomerVAT(int customerVAT) {
        this.customerVAT = customerVAT;
    }
}
