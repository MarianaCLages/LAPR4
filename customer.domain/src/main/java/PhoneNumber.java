import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {

    private int indicatives;
    private long pNumber;

    public PhoneNumber(final int indicatives, final long pNumber) throws IllegalAccessException {

        checkIndicatives(indicatives);
        checkpNumber(pNumber);

        this.indicatives = indicatives;
        this.pNumber = pNumber;
    }

    public PhoneNumber() {

    }

    public void checkIndicatives(int indicatives) throws IllegalAccessException {

        if(indicatives < 100 && indicatives > 999){
            throw new IllegalAccessException("Incorrect indicative format!");
        }
    }

    public void checkpNumber(long pNumber) throws IllegalAccessException {

        if(pNumber < 100000000 && pNumber > 999999999){
            throw new IllegalAccessException("Incorrect Phone Number format!");
        }

    }

    public int getIndicatives() {
        return indicatives;
    }

    public void setIndicatives(int indicatives) {
        this.indicatives = indicatives;
    }

    public long getpNumber() {
        return pNumber;
    }

    public void setpNumber(long pNumber) {
        this.pNumber = pNumber;
    }
}
