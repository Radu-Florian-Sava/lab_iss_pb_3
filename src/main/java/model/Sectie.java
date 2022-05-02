package model;


public class Sectie extends GenericActor {
    private String numeSectie;

    public Sectie(String numeUtilizator, String parola) {
        super(numeUtilizator, parola);
    }


    public String getNumeSectie() {
        return numeSectie;
    }

    public void setNumeSectie(String numeSectie) {
        this.numeSectie = numeSectie;
    }
}

