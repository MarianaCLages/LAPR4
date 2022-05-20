package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber implements ValueObject,Comparable<PhoneNumber> {

    private int indicatives;
    private long pNumber;

    public PhoneNumber(final int indicatives, final long pNumber) throws IllegalArgumentException {

        checkIndicatives(indicatives);
        checkpNumber(pNumber);

        this.indicatives = indicatives;
        this.pNumber = pNumber;
    }

    public PhoneNumber() {

    }

    public void checkIndicatives(int indicatives){

        if(indicatives < 100 || indicatives > 999){
            throw new IllegalArgumentException("Incorrect indicative format! (only 3 numbers allowed)!");
        }
    }

    public void checkpNumber(long pNumber)  {

        if(pNumber < 100000000 || pNumber > 999999999){
            throw new IllegalArgumentException("Incorrect Phone Number format! (only 9 numbers allowed)!");
        }

    }


    @Override
    public int compareTo(PhoneNumber o) {

        if( this.pNumber == o.pNumber && this.indicatives == this.indicatives){
            return 0;
        }
        else{
            return -1;
        }
    }
}
