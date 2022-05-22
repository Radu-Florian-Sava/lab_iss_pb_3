package model;

public class Medicament {
    private String denumire;
    private String descriere;
    private int doza;
    private boolean utilizabil;

    public Medicament(String denumire, String descriere, int doza) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.doza = doza;
        this.utilizabil = true;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getDoza() {
        return doza;
    }

    public void setDoza(int doza) {
        this.doza = doza;
    }

    public boolean isUtilizabil() {
        return utilizabil;
    }

    public void setUtilizabil(boolean utilizabil) {
        this.utilizabil = utilizabil;
    }
}
