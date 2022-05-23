package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Farmacist extends GenericActor {

    public Farmacist(String numeUtilizator, String parola) {
        super(numeUtilizator, parola);
    }

    public Farmacist() {
        super();
    }
}

