import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {

    private int indicatives;
    private long pNumber;

    public PhoneNumber(final int indicatives, final long pNumber){
        this.indicatives = indicatives;
        this.pNumber = pNumber;
    }

    public PhoneNumber() {

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
