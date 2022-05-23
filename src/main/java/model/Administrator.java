package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("0")
public class Administrator extends GenericActor {

    public Administrator(String numeUtilizator, String parola) {
        super(numeUtilizator, parola);
    }

    public Administrator() {

    }
}
