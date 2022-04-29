package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

@Entity
public class Photo implements ValueObject, Serializable {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private final byte[] photoInBytes;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long photoId;

    protected Photo(final byte[] photoInBytes) {
        Preconditions.nonEmpty(Collections.singleton(photoInBytes), "Photo should neither be null nor empty");
        this.photoInBytes = photoInBytes;
    }

    public Photo() {
        //For ORM purposes only
        this.photoInBytes = null;
    }

    public static Photo valueOf(final byte[] photoInBytes) {
        return new Photo(photoInBytes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo that = (Photo) o;
        return Arrays.equals(this.photoInBytes, that.photoInBytes);
    }
}
