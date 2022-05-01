package eapli.base.customermanagement.domain;


import eapli.base.customermanagement.dto.CustomerDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;


import javax.persistence.*;

@Entity
public class Customer implements AggregateRoot<Long>, DTOable<CustomerDTO> {

    private static final long serialVersionUID = 1L;
    @Version
    private Long version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Column(nullable = false)
    private PhoneNumber customerPhoneNumber;
    @Column(nullable = false)
    private Gender gender;
    @Column(nullable = false)
    private BirthDate birthDate;
    @Column(nullable = false)
    private Name name;
    @Column(nullable = false)
    private VAT vat;
    @Column(nullable = false)
    private Email email;
    @Column(nullable = false)
    private Address address;

    public Customer(final PhoneNumber customerPhoneNumber, final BirthDate birthDate,
                    final Name name, final Gender gender,
                    final VAT vat, final Email email, Address address) throws IllegalArgumentException {

        this.customerPhoneNumber = customerPhoneNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.name = name;
        this.vat = vat;
        this.email = email;
        this.address = address;
    }

    public Customer() {

    }

    public PhoneNumber phoneNumber() {
        return this.customerPhoneNumber;
    }

    public Gender gender() {
        return this.gender;
    }

    public BirthDate birthDate() {
        return this.birthDate;
    }

    public Name name() {
        return this.name;
    }

    public VAT vat() {
        return this.vat;
    }

    public Email email() {
        return this.email;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {

        if (!(other instanceof Customer)) {
            return false;
        }

        final Customer that = (Customer) other;

        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && gender.equals(that.gender) &&
                name.equals(that.name) &&
                email.equals(that.email) &&
                vat.equals(vat) &&
                customerPhoneNumber.equals(customerPhoneNumber) &&
                birthDate.equals(that.birthDate);
    }

    @Override
    public Long identity() {
        return customerId;
    }


    @Override
    public CustomerDTO toDTO() {
        return new CustomerDTO(name.toString(),vat.toString(),email.toString(),address.toString());
    }
}
