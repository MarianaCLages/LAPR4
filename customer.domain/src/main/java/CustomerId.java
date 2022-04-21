import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerId implements ValueObject, Comparable<CustomerId> {


    private Long id;

    public CustomerId(final Long id){
        this.id = id;
    }

    public CustomerId() {

    }

    @Override
    public int compareTo(final CustomerId o) {
        return this.compareTo(o);
    }
}
