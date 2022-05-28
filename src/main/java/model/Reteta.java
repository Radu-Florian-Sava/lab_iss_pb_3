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

    @Column(name = "cantitate")
    private Integer cantitate;

    @Column(name = "onorata")
    private Boolean onorata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numeSectie")
    private GenericActor numeSectie;

    public GenericActor getNumeSectie() {
        return numeSectie;
    }

    public void setNumeSectie(GenericActor numeSectie) {
        this.numeSectie = numeSectie;
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

    public Medicament getNumeMedicament() {
        return numeMedicament;
    }

    public void setNumeMedicament(Medicament numeMedicament) {
        this.numeMedicament = numeMedicament;
    }

    public RetetaId getId() {
        return id;
    }

    public void setId(RetetaId id) {
        this.id = id;
    }
}