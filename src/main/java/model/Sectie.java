package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Sectie extends GenericActor {
    public Sectie(String numeUtilizator, String parola) {
        super(numeUtilizator, parola);
    }

    public Sectie() {

    }

    @Override
    public String toString() {
        return "Sectie{" +
                "numeUtilizator='" + numeUtilizator + '\'' +
                '}';
    }
}

