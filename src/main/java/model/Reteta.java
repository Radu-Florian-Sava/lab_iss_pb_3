package model;

import javax.persistence.*;

@Entity
@Table(name = "retete")
public class Reteta {
    @EmbeddedId
    private RetetaId id;

    @MapsId("numeMedicament")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "numeMedicament", nullable = false)
    private Medicament numeMedicament;

    @MapsId("numeSectie")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "numeSectie", nullable = false)
    private GenericActor numeSectie;

    @Column(name = "cantitate")
    private Integer cantitate;

    @Column(name = "onorata")
    private Boolean onorata;

    public Reteta(RetetaId id, Medicament numeMedicament, GenericActor numeSectie, Integer cantitate) {
        this.id = id;
        this.numeMedicament = numeMedicament;
        this.numeSectie = numeSectie;
        this.cantitate = cantitate;
        this.onorata = false;
    }

    public Reteta() {
        this.id = new RetetaId();
    }

    public Boolean getOnorata() {
        return onorata;
    }

    public void setOnorata(Boolean onorata) {
        this.onorata = onorata;
    }

    public Integer getCantitate() {
        return cantitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }

    public GenericActor getNumeSectie() {
        return numeSectie;
    }

    public void setNumeSectie(GenericActor numeSectie) {
        this.numeSectie = numeSectie;
        id.setNumeSectie(numeSectie.getNumeUtilizator());
    }

    public Medicament getNumeMedicament() {
        return numeMedicament;
    }

    public void setNumeMedicament(Medicament numeMedicament) {
        this.numeMedicament = numeMedicament;
        id.setNumeMedicament(numeMedicament.getDenumire());
    }

    public RetetaId getId() {
        return id;
    }

    public void setId(RetetaId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reteta{" + id + '}';
    }
}