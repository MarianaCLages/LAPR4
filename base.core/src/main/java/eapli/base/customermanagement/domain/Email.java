package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Email implements ValueObject, Comparable<Email> {

    private String email;

    public Email(final String email) throws IllegalArgumentException {
        checkEmail(email);
        this.email = email;
    }

    public Email() {

    }

    public void checkEmail(String email) {

        if (!email.contains("@") || email.charAt(0) == '@') {
            throw new IllegalArgumentException("Incorrect Email Format!");
        }
    }


    @Override
    public int compareTo(Email o) {

        if (this.email.equals(o.email)) return 0;
        else return -1;
    }

    @Override
    public String toString() {
        return this.email;
    }

    public static Email valueOf(String email) {
        return new Email(email);
    }

}
