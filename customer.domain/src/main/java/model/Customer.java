package model;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long customerId;

    @Embedded
    private PhoneNumber customerPhoneNumber;
    @Embedded
    private CustomerGender customerGender;
    @Embedded
    private CustomerBirthDate customerBirthDate;
    @Embedded
    private CustomerName customerName;
    @Embedded
    private CustomerVAT customerVAT;
    @Embedded
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
