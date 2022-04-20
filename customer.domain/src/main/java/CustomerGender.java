import javax.persistence.Embeddable;

@Embeddable
public class CustomerGender {


    private String gender;

    public CustomerGender(String gender) throws IllegalAccessException {
        checkGender(gender);
        this.gender = gender;
    }


    public CustomerGender() {

    }

    public void checkGender(String customerGender) throws IllegalAccessException {
        if(! customerGender.equals("Male") || ! customerGender.equals("Female") || ! customerGender.equals("Other"))
            throw new IllegalAccessException("Gender does not exist!");
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
