package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class Payment implements ValueObject {

    private final PaymentMethod paymentMethod;

    public Payment(final PaymentMethod paymentMethod) {
        Preconditions.noneNull(paymentMethod, "Please enter a valid ");

        this.paymentMethod = paymentMethod;

    }

    public Payment() {
        //For ORM purposes only
        this.paymentMethod = null;
    }

    public static Payment valueOf(final PaymentMethod paymentMethod) {
        return new Payment(paymentMethod);
    }

    public PaymentMethod paymentMethod() {
        return this.paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentMethod == payment.paymentMethod;
    }

    @Override
    public String toString() {
        return "" + paymentMethod;
    }

}
