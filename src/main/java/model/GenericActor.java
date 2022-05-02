package model;

public class GenericActor {
    protected String numeUtilizator;
    protected String parola;

    public GenericActor(String numeUtilizator, String parola) {
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
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
