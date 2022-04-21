import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer implements AggregateRoot<CustomerId> {

    @Id
    private CustomerId customerId;

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


    public Customer(final PhoneNumber customerPhoneNumber,final CustomerBirthDate customerBirthDate, final CustomerName customerName,final CustomerGender customerGender, final CustomerVAT customerVAT,final CustomerEmail customerEmail) throws IllegalAccessException {

        this.customerPhoneNumber = customerPhoneNumber;
        this.customerGender = customerGender;
        this.customerBirthDate = customerBirthDate;
        this.customerName = customerName;
        this.customerVAT = customerVAT;
        this.customerEmail = customerEmail;
    }

    public Customer() {

    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public PhoneNumber getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(PhoneNumber customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public CustomerGender getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(CustomerGender customerGender) {
        this.customerGender = customerGender;
    }

    public CustomerBirthDate getCustomerBirthDate() {
        return customerBirthDate;
    }

    public void setCustomerBirthDate(CustomerBirthDate customerBirthDate) {
        this.customerBirthDate = customerBirthDate;
    }

    public CustomerName getCustomerName() {
        return customerName;
    }

    public void setCustomerName(CustomerName customerName) {
        this.customerName = customerName;
    }

    public CustomerVAT getCustomerVAT() {
        return customerVAT;
    }

    public void setCustomerVAT(CustomerVAT customerVAT) {
        this.customerVAT = customerVAT;
    }

    public CustomerEmail getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(CustomerEmail customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public CustomerId identity() {
        return this.customerId;
    }
}
