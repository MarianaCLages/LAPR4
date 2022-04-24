package eapli.base.customermanagement.domain;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;


import javax.persistence.*;

@Entity
public class Customer implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;
    @Version
    private Long version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;


    private PhoneNumber customerPhoneNumber;
    private CustomerGender customerGender;
    private CustomerBirthDate customerBirthDate;
    private CustomerName customerName;
    private CustomerVAT customerVAT;
    private CustomerEmail customerEmail;


    public Customer(final PhoneNumber customerPhoneNumber, final CustomerBirthDate customerBirthDate, final CustomerName customerName, final CustomerGender customerGender, final CustomerVAT customerVAT, final CustomerEmail customerEmail) throws IllegalArgumentException {

        this.customerPhoneNumber = customerPhoneNumber;
        this.customerGender = customerGender;
        this.customerBirthDate = customerBirthDate;
        this.customerName = customerName;
        this.customerVAT = customerVAT;
        this.customerEmail = customerEmail;
    }

    public Customer() {

    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {

        if(! (other instanceof Customer)){
            return false;
        }

        final Customer that =(Customer) other;

        if(this == that){
            return true;
        }

        return identity().equals(that.identity()) && customerGender.equals(that.customerGender) &&
                customerName.equals(that.customerName) &&
                customerEmail.equals(that.customerEmail) &&
                customerVAT.equals(customerVAT) &&
                customerPhoneNumber.equals(customerPhoneNumber) &&
                customerBirthDate.equals(that.customerBirthDate);
    }

    @Override
    public Long identity() {
        return customerId;
    }
}
