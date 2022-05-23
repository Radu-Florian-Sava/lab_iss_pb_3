package model;

import javax.persistence.*;

@Entity
@Table(name="genericactors")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="userType", discriminatorType = DiscriminatorType.INTEGER)
public class GenericActor {
    @Id
    @Column(name = "username")
    protected String numeUtilizator;

    @Column(name = "password")
    protected String parola;

    public GenericActor(String numeUtilizator, String parola) {
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
    }

    public GenericActor() {

    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
