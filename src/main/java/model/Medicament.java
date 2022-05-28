package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "medicamente")
public class Medicament {
    @Id
    @Column(name = "denumire")
    private String denumire;
    @Column(name = "descriere")
    private String descriere;
    @Column(name = "doza")
    private int doza;
    @Column(name = "utilizabil")
    private boolean utilizabil;

    public Medicament(String denumire, String descriere, int doza) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.doza = doza;
        this.utilizabil = true;
    }

    public Medicament() {

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

    @Override
    public String toString() {
        return "Medicament{" +
                "denumire='" + denumire + '\'' +
                ", doza=" + doza +
                '}';
    }
}
